package zbc.com.calclib

import kotlinx.coroutines.*
import kotlin.coroutines.resumeWithException


//协程的取消机制


suspend fun getUserCoroutineCancelAble(): UserBean {

    return suspendCancellableCoroutine<UserBean> { cancellableContinuation ->


        getUserData(object : Callback<UserBean> {
            override fun onSuccess(value: UserBean) {
                cancellableContinuation.resume(value, onCancellation = {})
            }

            override fun onError(t: Throwable) {
                cancellableContinuation.resumeWithException(t)
            }
        })
        cancellableContinuation.invokeOnCancellation {

            logScope("在这里执行程序的取消操作")
        }
    }
}


suspend fun cancelDemo() {
    logScope("1")
    val job = GlobalScope.launch {
        logScope("2")
        val user = getUserCoroutineCancelAble()
        println("${user.name} , ${user.age}")
        logScope("3")
    }

    job.cancelAndJoin()
    logScope("4")

}




suspend fun coroutineDispatcher(){

        // 创建一个单线程的协程调度器，下面两个协程都运行在这同一线程上
        val coroutineDispatcher = newSingleThreadContext("ctx")
        // 启动协程 1
        GlobalScope.launch(coroutineDispatcher) {
            logScope("the first coroutine")
            delay(200)
            logScope("the first coroutine")
        }
        // 启动协程 2
        GlobalScope.launch(coroutineDispatcher) {
            logScope("the second coroutine")
            delay(100)
            logScope("the second coroutine")
        }
        // 保证 main 线程存活，确保上面两个协程运行完成
        Thread.sleep(500)

}




suspend fun main(args: Array<String>) {
    coroutineDispatcher()

}




