package zbc.com.cn

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


/**
 * 创建一个可以在java中使用的协程
 */
fun loadString() = runBlocking {
        async { load() }.await()
}