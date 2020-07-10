package zbc.com.calclib

import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*


//协程挂起

fun suspendDemo1() {
    runBlocking {
        launch { // 默认继承 parent coroutine 的 CoroutineDispatcher，指定运行在 main 线程
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
            delay(100)
            println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) {
            println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
            delay(100)
            println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
        }
    }
}

fun suspendCorotine() {
    runBlocking {
        val result = suspendCancellableCoroutine<String> { cancellableContinuation ->
            cancellableContinuation.resume("\"Hello Word\"") { cause: Throwable ->
                println("输出函数")
                throw Exception("bug")

            }
        }
        println(result)
    }
}

suspend fun firstSuspend(): String {

    return suspendCoroutineUninterceptedOrReturn<String> { continuation ->
        continuation.resume("Hello Word ！")
        logScope("虽然协程返回了结果，但是我依然要打印")
        //函数的返回值必须是这个，不然程序将会报错 jvm.internal.ContinuationImpl.releaseIntercepted
        kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
    }

}

class MyCoroutine : Continuation<String> {
    override fun resumeWith(result: Result<String>) {
        logScope("MyCoroutine 回调resumeWith 返回的结果 " + result.getOrNull())
    }

    override val context: CoroutineContext
        get() = EmptyCoroutineContext

}

fun defaultResumeIo() {
    val lambda = suspend {
        val firstSuspend = firstSuspend()
        logScope("返回的结果是" + firstSuspend)
        firstSuspend
    }

    val myCoroutine = MyCoroutine()
    lambda.startCoroutine(myCoroutine)
}

fun main(args: Array<String>) {
    GlobalScope.launch (start = CoroutineStart.UNDISPATCHED){
        defaultResumeIo()
    }

}