package zbc.com.cn

import android.util.Log
import kotlinx.coroutines.*
import java.lang.Exception

/**
 * Created by benchengzhou on 2020/9/3  17:41 .
 * 作者邮箱： mappstore@163.com
 * 功能描述： 协程async 返回数据实验
 * 类    名：
 * 备    注：
 */

fun main(args: Array<String>) = runBlocking {
    "-1".logWithThreadInfo()
    //async 默认执行在 CoroutineStart.DEFAULT 中的
    val job = GlobalScope.async {
        "1".logWithThreadInfo()
        for (i in (1..10000)) {
            //协程的挂起需要用户在程序中主动判断程序是否出现挂起标志 !isActive 并做出对应的处理
            if (!isActive) break
            "${i} in looper".logWithThreadInfo()
        }
        "2".logWithThreadInfo()
        "协程任务执行完成"
    }
    "-2".logWithThreadInfo()
    //job.cancel()取消协程整个协程会报错，退出虚拟机
    "-3".logWithThreadInfo()
    job.await().logWithThreadInfo()
    "-4".logWithThreadInfo()
}