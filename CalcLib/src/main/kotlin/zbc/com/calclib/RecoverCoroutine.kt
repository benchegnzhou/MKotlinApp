package zbc.com.calclib

import kotlinx.coroutines.*

//协程的挂起和恢复

fun CoroutineRecover() {
    runBlocking { // 新建并启动 blocking 协程，运行在 main 线程上，等待所有子协程运行完成后才会结束
        launch(Dispatchers.Unconfined) { // 新建并启动 launch 协程，没有指定所运行线程，一开始运行在调用者所在的 main 线程上
            println("${Thread.currentThread().name} : launch start")
            async(Dispatchers.Default) { // 新建并启动 async 协程，运行在 Dispatchers.Default 的线程池中
                println("${Thread.currentThread().name} : async start")
                delay(100)  // 挂起 async 协程 100 ms
                println("${Thread.currentThread().name} : async end")
            }.await() // 挂起 launch 协程，直到 async 协程结束
            println("${Thread.currentThread().name} : launch end")
        }
    }
}



fun main(args: Array<String>) {
    CoroutineRecover()

}


