package zbc.com.calclib

import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserBean {
    var name: String = "xiaoming"
    var age: Int = 24
}

interface Callback<T> {
    fun onSuccess(value: T)

    fun onError(t: Throwable)
}


fun getUserData(callback: Callback<UserBean>) {
    val bean = UserBean()
    bean.name = "小明"
    bean.age = 24
        return callback.onSuccess(bean)
//    return callback.onError(Exception("网络请求失败"))
}

suspend fun getUserCoroutine(): UserBean {

    return suspendCoroutine<UserBean> { continuation ->

        getUserData(object : Callback<UserBean> {
            override fun onSuccess(value: UserBean) {

                continuation.resume(value)
            }

            override fun onError(t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}


suspend fun defaultScope() {
    val job = GlobalScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
        println("--GlobalScope 捕获到异常信息--")
        throwable.printStackTrace()
    }) {

        GlobalScope.launch {
            println("1# 内部协程开始了")
            delay(1000)
            println("1# 内部协程结束了")
        }
        launch {
            println("2# 内部协程开始了")
            delay(1000)
            println("2# 内部协程结束了")
        }

        launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            println("--launch 捕获到异常信息--")
            throwable.printStackTrace()
        }) {
            println("协程执行开始")
            val user = getUserCoroutine()
            println("${user.name} , ${user.age}")

            println("协程执行结束")
        }.join()
    }

    job.join()
}


suspend fun supervisorScope() {
    val job = GlobalScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
        println("--GlobalScope 捕获到异常信息--")
        throwable.printStackTrace()
    }) {

        GlobalScope.launch {
            println("1# 内部协程开始了")
            delay(1000)
            println("1# 内部协程结束了")
        }
        launch {
            println("2# 内部协程开始了")
            delay(1000)
            println("2# 内部协程结束了")
        }

        //使用supervisorScope作用域启动协程，作用域中的异常会被自己捕获不会往外传递异常，但是作用域中的协程是可以被顶级作用域取消的
        supervisorScope {
            launch(CoroutineExceptionHandler { coroutineContext, throwable ->
                println("--launch 捕获到异常信息--")
                throwable.printStackTrace()
            }) {
                println("协程执行开始")
                val user = getUserCoroutine()
                println("${user.name} , ${user.age}")

                println("协程执行结束")
            }.join()
        }
    }

    job.join()
}


suspend fun coroutineScope() {
    val job = GlobalScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
        println("--GlobalScope 捕获到异常信息--")
        throwable.printStackTrace()
    }) {

        GlobalScope.launch {
            println("1# 内部协程开始了")
            delay(1000)
            println("1# 内部协程结束了")
        }
        launch {
            println("2# 内部协程开始了")
            delay(1000)
            println("2# 内部协程结束了")
        }

        //coroutineScope 启动的作用域，其实我们默认不写就是使用的这种作用域,这种作用域中的协程异常不会被自己捕获，只能向顶级作用域抛出
        coroutineScope {
            launch(CoroutineExceptionHandler { coroutineContext, throwable ->
                println("--launch 捕获到异常信息--")
                throwable.printStackTrace()
            }) {
                println("协程执行开始")
                val user = getUserCoroutine()
                println("${user.name} , ${user.age}")

                println("协程执行结束")
            }.join()
        }
    }

    job.join()
}


suspend fun main(args: Array<String>) {

    coroutineScope()
    /* GlobalScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
         println("异常信息")
     }) {
         launch {
             delay(2000)
             println("launch 执行中")
         }
         launch {
             val user = getUserCoroutine()
             println("${user.name} , ${user.age}")
         }.join()

         println("执行中")

     }.join()*/

    println("程序执行完成")
}