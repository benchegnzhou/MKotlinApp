package zbc.com.cn

import kotlinx.coroutines.*

private val threadPoolDispatcher = newSingleThreadContext("MCustomThread")

fun main(args: Array<String>) = runBlocking(Dispatchers.Default) {
    "1".logWithThreadInfo()
    val job = launch(threadPoolDispatcher) {
        "协程中的日志 1".logWithThreadInfo()
        delay(1000L)
        "协程中的日志 2".logWithThreadInfo()
    }
    "2".logWithThreadInfo()
    job.join()
    "3".logWithThreadInfo()
}