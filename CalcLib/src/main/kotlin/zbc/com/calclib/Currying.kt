package zbc.com.calclib

import java.io.OutputStream
import java.nio.charset.Charset


/**定义一个Hello的方法*/
//传入三个参数，返回一个Boolean类型的值
fun hello(x: String, y: Long, z: Int): Boolean {
    return x.isNotEmpty() && y > 0 && z % 2 != 0
}

fun curriedHello(x: String): (y: Long) -> (z: Int) -> Boolean {
    return fun(y: Long): (z: Int) -> Boolean {
        return fun(z: Int): Boolean {
            println("函数打印 x=$x , y = $y ，z = $z")
            return x.isNotEmpty() && y > 0 && z % 2 != 0
        }
    }
}

/**
 * 写一个打日志的东西
 */
fun log(tag: String, target: OutputStream, message: Any?) {
    //打日志"\n"是换行的意思
    target.write("[$tag] $message\n".toByteArray())
}


//fun log(tag: String) = fun(target: OutputStream) = fun(message: Any?) =
//    //打日志"\n"是换行的意思
//    target.write("[$tag] $message\n".toByteArray())
/*fun log(tag: String): (target: OutputStream) -> (message: Any?) -> Unit {
    return fun(target: OutputStream): (message: Any?) -> Unit {
        return fun(message: Any?): Unit {
            //打日志"\n"是换行的意思
            target.write("[$tag] $message\n".toByteArray())
        }
    }
}*/


fun <P1, P2, P3, R> Function3<P1, P2, P3, R>.curried() =
    fun(p1: P1) = fun(p2: P2) = fun(p3: P3) = this(p1, p2, p3)

val makeString = fun(byteArray: ByteArray, charset: Charset): String {
    return String(byteArray, charset)
}

fun <P1, P2, R> Function2<P1, P2, R>.partial(p2: P2) = fun(p1: P1) = this(p1, p2)
fun <P1, P2, R> Function2<P1, P2, R>.partial2(p1: P1) = fun(p2: P2) = this(p1, p2)

val gbkStr = makeString.partial(charset("GBK"))
val gbkStr2 = makeString.partial2("中国你好".toByteArray(charset("GBK")))
fun main(args: Array<String>) {
    val string2ByteArray = "中国你好".toByteArray(charset("GBK"))
    gbkStr(string2ByteArray).println()
    gbkStr2(charset("GBK")).println()

//    hello("1", 2, 3).println()
//    curriedHello("1")(2)(3).println()
//    log("Currying", System.out, "这里是输出的日志信息")
//    log("Currying")(System.out)("这里是输出的日志信息")
    ::log.curried()("Currying")(System.out)("这里是输出的日志信息")

    var partialFunction = ::log.curried()("partialFunctionLog")(System.out)
    partialFunction("日志信息1")
    partialFunction("日志信息2")
    partialFunction("日志信息3")
}