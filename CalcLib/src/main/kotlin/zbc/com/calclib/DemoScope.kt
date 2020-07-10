package zbc.com.calclib

import kotlinx.coroutines.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext


fun logScope(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun demoScope() {
    runBlocking<Unit> {

        //例子开始
        launch(Dispatchers.Unconfined) { // 非受限 -- 将和主线程中运行
            println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
            delay(500)
            println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
        }
        launch { // 父协程的上下文，主runBlocking协程
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
            delay(1000)
            println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
        }
        //例子结束
    }
}


fun asyncScope() {
    runBlocking<Unit> {
        //例子开始
        val a = async {
            logScope("I'm computing a piece of the answer")
            6
        }
        val b = async {
            logScope("I'm computing another piece of the answer")
            7
        }
        logScope("The answer is ${a.await() * b.await()}")
        //例子结束
    }
}


fun newSingleThreadScope() {
    //例子开始
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                logScope("Started in ctx1")
                withContext(ctx2) {
                    logScope("Working in ctx2")
                }
                logScope("Back to ctx1")
            }
        }
    }
    //例子结束
}

fun JobScope() {
    runBlocking<Unit> {
        //例子开始
        println("My job is ${coroutineContext[Job]}")
        //例子结束
    }
}

fun GlobalScope2() {
    runBlocking {
        //例子开始
        // 启动协程来处理某种传入请求
        val request = launch {
            //它产生了另外两个Job，一个是GlobalScope
            GlobalScope.launch {
                println("job1: I run in GlobalScope and execute independently!")
                delay(1000)
                println("job1: I am not affected by cancellation of the request")
            }
            // 另外一个继承了父上下文
            launch {
                delay(100)
                println("job2: I am a child of the request coroutine")
                delay(1000)
                println("job2: I will not execute this line if my parent request is cancelled")
            }
        }
        delay(500)
        request.cancel() // 取消请求的处理
        delay(1000) // 延迟一秒看看发生什么
        println("main: Who has survived request cancellation?")
        //例子结束
    }
}


fun scopeJoin() {
    runBlocking {
        //例子开始
        // 启动协程来处理某种传入请求
        val request = launch {
            repeat(3) { i -> // 启动一些子job
                launch {
                    delay((i + 1) * 200L) //不同的延时200ms、400ms、600ms
                    println("Coroutine $i is done")
                }
            }
            println("request: I'm done and I don't explicitly join my children that are still active")
        }
        request.join() // 等待请求结束，包括所有子协程
        println("Now processing of the request is complete")
        //例子结束
    }
}

fun pCoroutineName() {
    runBlocking {
        //例子开始
        logScope("Started main coroutine")
        // 运行两个后台值计算
        val v1 = async(CoroutineName("v1coroutine")) {
            delay(500)
            logScope("Computing v1")
            252
        }
        val v2 = async(CoroutineName("v2coroutine")) {
            delay(1000)
            logScope("Computing v2")
            6
        }
        logScope("The answer for v1 / v2 = ${v1.await() / v2.await()}")
        //例子结束
    }
}


fun mulitCoroutineContext() {
    runBlocking {
        //例子开始
        launch(Dispatchers.Default + CoroutineName("test")) {
            println("I'm working in thread ${Thread.currentThread().name}")
        }
        //例子结束
    }
}


class aAct : CoroutineScope {
    lateinit var job: Job

    fun create() {
        job = Job()
    }

    fun destroy() {
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    // Activity类继续
    fun doSomething() {
        // 为了演示，启动十个协程，每个协程运行不同的时间
        repeat(10) { i ->
            launch {
                delay((i + 1) * 200L) // 不同延时200ms, 400ms, ... 等待
                println("Coroutine $i is done")
            }
        }
    }
}


fun ScopeCancel() {
    runBlocking {
        //例子开始
        val activity = aAct()
        activity.create() // 创建activity
        activity.doSomething() // 运行测试函数
        println("Launched coroutines")
        delay(500L) // 延迟半秒
        println("Destroying activity!")
        activity.destroy() // 取消所有协程
        delay(1000) // 目视确认它们不起作用
        //例子结束
    }
}


val threadLocal = ThreadLocal<String?>() // 声明线程本地变量
fun threadLocal() {
    runBlocking {
        //sampleStart
        threadLocal.set("main")
        println("Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
            println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
            yield()
            println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        }
        job.join()
        println("Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        //sampleEnd
    }
}


fun interceptor() {
    runBlocking {
        val job =
            GlobalScope.launch(Dispatchers.IO + CoroutineName("demo_custom_interceptor") + MyContinuationInterceptor()) {
                logScope("1")
                delay(1000)
                logScope("2")
            }
        job.join()
    }
}


//自定义拦截器
class MyContinuationInterceptor : ContinuationInterceptor {
    override val key: CoroutineContext.Key<*>
        get() = ContinuationInterceptor

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return MyContinuation(continuation)
    }
}


class MyContinuation<T>(val continuation: Continuation<T>) : Continuation<T> {

    private val executor = Executors.newSingleThreadExecutor {
        Thread(it, "MyCreateThread")
            .also {
                //将线程作为幽灵线程
                it.isDaemon = true
            }
    }
    override val context: CoroutineContext
        get() = continuation.context

    override fun resumeWith(result: Result<T>) {
        logScope("调试日志打印")
        println(result)
        //这句代码决定了挂起函数之后的代码段执行在哪个线程当中
        executor.submit {
            logScope("函数调用开始")
            continuation.resumeWith(result)
            logScope("函数调用结束")
        }

        logScope("日志打印成功")
    }
}


fun main(args: Array<String>) {

    interceptor()
}