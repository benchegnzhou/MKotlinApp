package zbc.com.cn

import kotlinx.coroutines.*

/**
 * https://www.jianshu.com/p/51a4ecc1902a
 * runBlocking
 * 官方解释 Runs a new coroutine and blocks the current thread interruptibly until its completion
 *
 *  这里用runBlocking创建来一个协程并立即执行，按照runBlocking签名上的注释来说，
 *  runBlocking会阻塞当前线程，所以，我们在协程里用delay来挂起协程，看看打印结果：
 *
 * coroutineScope
 * Creates a [CoroutineScope] and calls the specified suspend block with this scope.
 * coroutineScope只是一个suspend function，它和runBlocing有本质的区别。
 * 这里我们用GlobalScope来创建一个顶级的协程，并设置start参数为UNDISPATCHED，立即执行。
 *
 * 很显然，coroutineScope在执行完delay挂起之前的代码后，就立即将控制权交给了所在的线程，线程并没有被阻塞，
 * 并立即去执行协程外面的事情去了。最后挂起时间到了后，才又将控制权交回协程，继续执行delay后面的代码。
 *
 * 由上面两个例子可见，runBlocking和coroutineScope除了有本质的区别外，最大的不同就在于两者所在的协程被挂起后对所在线程的影响，
 * runBlocking所在协程被挂起后会阻塞所在线程，线程不能处理协程之外的事情；coroutineScope所在的协程被挂起后，则会立即交出控制权给所在的线程，不会阻塞线程，线程可以处理协程之外的事情。
 *
 *
 * 详细的文章参考这个
 * https://blog.csdn.net/tigershin/article/details/86482808
 *
 * runBlocking启动协程，在当前线程调度，并阻塞当前线程
 */
fun main(args: Array<String>) = runBlocking {

    "1".logWithThreadInfo()
    //直接调用launch 相当于继承自父作用域中的协程上下文，函数有一个返回值
    val job: Job = launch {
        // 这段代码在作用域里启动了一个新协程
        // 它可以调用挂起函数
        "协程中中输出了-1".logWithThreadInfo()
        delay(1000L)
        "协程中中输出了-2".logWithThreadInfo()
        println("${Thread.currentThread()}")
    }

    "2".logWithThreadInfo()
    //join 函数可以让父协程一直阻塞，一直到join的协程执行完成
    job.join()
    "3".logWithThreadInfo()

}