package zbc.com.cn

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

//每个协程就是一个线程，当多个协程同时处理一个数据时，就会出现线程安全问题，
//使用 AtomicInteger 线程安全的可以有效的解决线程安全问题
//var foo = AtomicInteger(0)
var foo = 1
fun main(args: Array<String>) = runBlocking {
    List(10000) {
        launch {
            repeat(100000) {
//                foo.getAndAdd(1)
                foo++
            }
        }
    }.forEach {
      it.join()
    }
    "${foo}".logWithThreadInfo()

}