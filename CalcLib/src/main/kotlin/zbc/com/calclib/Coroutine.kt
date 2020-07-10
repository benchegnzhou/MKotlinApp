package zbc.com.calclib


import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.EmptyCoroutineContext


//public fun CoroutineScope.launch(
//    context: CoroutineContext = EmptyCoroutineContext,
//    start: CoroutineStart = CoroutineStart.DEFAULT,
//    block: suspend CoroutineScope.() -> Unit
//): Job {
//    println("协程已经启动了")
//    return context.toExperimentalCoroutineContext();
//}


suspend fun work() {
    //使用 async 的协程将会自动执行
    var deferred: Deferred<String> = GlobalScope.async {
        println("子线程，开始烧水")
        delay(2000)
        println("子线程，正在烧水")
        delay(2000)
        println("子线程，水烧开了")
        "烧开的热水"
    }

    println("主线程，开始拖地")
    delay(1000)
    println("主线程，拖地完成")
    val str = deferred.await()
    println("主线程，使用 $str 洗碗")

}


suspend fun coroutineCancel() {
    println("协程开始的位置")
    // 在后台启动一个新的协程并继续
    val job = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
        println("协程代码开始了")

        delay(1000L)  //   挂起函数，底层方法调用了实现CancellableContinuationImpld接口的挂起函数，`job.cancel()`调用后，遇到这个函数程序将会被调用

        println("协程代码结束了") // 在延迟后打印输出
        println("协程中 当前线程的名称 ${Thread.currentThread().name}")
    }

    println("协程结束的位置")
    delay(100L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
    //    job.start()

    job.cancel()
    job.join()

    println("Hello,") // 协程已在等待时主线程还在继续
    println("协程外 当前线程的名称 ${Thread.currentThread().name}")
    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
}

suspend fun main(args: Array<String>) {

//    println("协程开始的位置")
//    val job = GlobalScope.launch(start = CoroutineStart.DEFAULT) { // 在后台启动一个新的协程并继续
//        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
//        println("World!") // 在延迟后打印输出
//        println("协程中 当前线程的名称 ${Thread.currentThread().name}")
//    }
//
//    println("协程结束的位置")
//    job.join()
//
//    println("Hello,") // 协程已在等待时主线程还在继续
//    println("协程外 当前线程的名称 ${Thread.currentThread().name}")
//    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活


//    val job = GlobalScope.launch(start = CoroutineStart.DEFAULT) {
//        delay(3000L)
//        println("协程中 当前线程的名称 ${Thread.currentThread().name}")
//        println("Hello,")
//    }
//    println("协程外 当前线程的名称 ${Thread.currentThread().name}")
//    println("World!")
//    job.start()
//
//    Thread.sleep(5000L)
//    println("协程结束的位置")
//
//
//    val deferred: Deferred<String> = GlobalScope.async(start = CoroutineStart.DEFAULT) {
//        println("deferred 我执行到了")
//        println("协程中 当前线程的名称 ${Thread.currentThread().name}")
//        "Hello deferred"
//    }
//    deferred.start()
//    val await = deferred.await()
//    println(await)
//
//    Thread.sleep(5000L)
//    println("程序运行结束")
//    coroutineCancel()

    demo2()
}

fun demo2() {

    runBlocking<Unit> {
        //例子开始
        launch { // 父协程的上下文，主runBlocking协程
            println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) { // 非受限 -- 将和主线程中运行
            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) { // 将调度到DefaultDispatcher
            println("Default               : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyOwnThread")) { // 将有自己的线程
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }
        //例子结束
    }

    println("主程序线程            : I'm working in thread ${Thread.currentThread().name}")
}

