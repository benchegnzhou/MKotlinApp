package zbc.com.cn

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import zbc.com.cn.utils.otherwise
import zbc.com.cn.utils.yes

import kotlin.concurrent.thread

interface CallBack {
    fun onSuccess(msg: String)
    fun onError(e: Exception)

}

fun main(args: Array<String>) {

    runBlocking {
        val load = load()
        load.logWithThreadInfo()
    }
    /*loadAsync(object : CallBack {
        override fun onSuccess(msg: String) {
            msg.logWithThreadInfo()
        }

        override fun onError(e: Exception) {
            e.printStackTrace()
        }

    })*/
}


/**
 * 使用协程的用法
 */
suspend fun load(): String {
    /** CompletableDeferred 本质上是一个 Job
     *
     */
    val completableDeferred: CompletableDeferred<String> = CompletableDeferred()
    loadAsync(object : CallBack {
        override fun onSuccess(msg: String) {
            completableDeferred.complete(msg)
        }

        override fun onError(e: Exception) {
            completableDeferred.completeExceptionally(e)
        }
    })

    return completableDeferred.await()

}


/**
 * 不使用协程时的用法
 */
fun loadAsync(callBack: CallBack) {
    thread {
        try {
            Thread.sleep(500)
            (Math.random() < 0.5).yes {
                callBack.onSuccess("HelloWord")
            }.otherwise {
                callBack.onError(Exception("This is a Demonstration Error."))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}