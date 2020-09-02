package zbc.com.cn

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(args: Array<String>) {
    println(1)
    GlobalScope.launch {
        // 这段代码在作用域里启动了一个新协程
        // 它可以调用挂起函数
        println("协程中中输出了-1")


        val job = launch {
            println("协程中的协程1")
            println(10 / 0)
            println("协程中的协程2")
        }
        try {
            job.join()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        delay(1000L)
        println("协程中中输出了-2")
    }
    println(2)
    //协程本质上也是线程，java中主线程启动的子线程默认为幽灵线程，当虚拟机中主线程结束后
    //虚拟机将会退出，幽灵线程也会被杀掉，增加延时卡主主线程防止虚拟机退出
    Thread.sleep(5000L)
    println(3)
}