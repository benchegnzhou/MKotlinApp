package zbc.com.cn

import java.text.SimpleDateFormat
import java.util.*

val dateFormat = SimpleDateFormat("HH:mm:ss:SS")

val now = {
    dateFormat.format(Date(System.currentTimeMillis()))
}


fun String.log(msg: String) {
    println("${now()} , The code run on ${Thread.currentThread()} ${msg} ")
}


fun String.logWithThreadInfo() {
    println("${now()} , The code run on ${Thread.currentThread()} ${this} ")
}