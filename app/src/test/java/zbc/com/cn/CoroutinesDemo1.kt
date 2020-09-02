package zbc.com.cn

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(args: Array<String>) {
    "1".logWithThreadInfo()
    GlobalScope.launch {
        // 这段代码在作用域里启动了一个新协程
        // 它可以调用挂起函数
        "协程中中输出了-1".logWithThreadInfo()
        delay(1000L)
        "协程中中输出了-2".logWithThreadInfo()
    }
    "2".logWithThreadInfo()
    //协程本质上也是线程，java中主线程启动的子线程默认为幽灵线程，当虚拟机中主线程结束后
    //虚拟机将会退出，幽灵线程也会被杀掉，增加延时卡主主线程防止虚拟机退出
    Thread.sleep(2000L)
    "3".logWithThreadInfo()
}