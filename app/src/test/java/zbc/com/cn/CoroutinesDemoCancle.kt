package zbc.com.cn

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by benchengzhou on 2020/9/3  17:41 .
 * 作者邮箱： mappstore@163.com
 * 功能描述： 协程取消实验
 * 类    名：
 * 备    注：
 */

fun main(args: Array<String>) = runBlocking {
    "-1".logWithThreadInfo()
    val job = GlobalScope.launch {
        "1".logWithThreadInfo()
        for (i in (1..10000)) {
            //协程的挂起需要用户在程序中主动判断程序是否出现挂起标志 !isActive 并做出对应的处理
            if (!isActive) break
            "${i} in looper".logWithThreadInfo()
        }
        "2".logWithThreadInfo()
    }
    "-2".logWithThreadInfo()
    job.cancel()
    "-3".logWithThreadInfo()
    job.join()
    "-4".logWithThreadInfo()
}