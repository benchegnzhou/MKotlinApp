package zbc.com.calclib

import java.io.BufferedReader
import java.io.FileReader
import java.lang.StringBuilder
import javax.swing.text.html.HTMLDocument


fun logIt(it: Any) {
    println(it)
}
/*

class MyInt{
    open fun showMsg(msg: Any?) {
        print("$msg ")
    }

    open fun showMsg(): String {
        return "没有参数 "
    }
}
*/

class MyInt {
    var num: String = "default"
    fun test(name: (String) -> Unit) {
        name(this.num)
    }
}

fun String.displayChars(name: (Char) -> Unit) {
    println(this)
    for (element in this) name(element)
}

//val display: (MyInt) -> Unit = MyInt::show
fun MyInt.test() {
    println("输出测试信息")
}

/**
 * 求1到num的阶乘
 */
fun factorial(num: Long): Long {
    if (num <= 0L) return 1
    return (1..num).reduce { acc, l -> acc * l }
}

data class PersonMan(var name: String, var age: Int) {
    fun work(workName: String) {
        println("$name $age 岁，是一个: $workName")
    }
}


fun findPerson(): PersonMan? {
    return PersonMan("xiaoming", 24)
}

fun main(args: Array<String>) {

    BufferedReader(FileReader("G:\\MKotlinApp\\CalcLib\\src\\main\\kotlin\\zbc\\com\\calclib\\HightLevelDemo.kt")).use {

        var lines: String?
        while (true) {
            lines = it.readLine() ?: break
            println(lines)
        }
    }


    var reader = BufferedReader(FileReader("G:\\MKotlinApp\\CalcLib\\src\\main\\kotlin\\zbc\\com\\calclib\\HightLevelDemo.kt"))



    with(reader) {
        var lines: String?
        while (true) {
            lines = readLine() ?: break
//            println(lines)
        }
        close()
    }

    findPerson()?.apply {
        work("律师")
        println(name)
        println(age)
    }

    var xiaoming = PersonMan("xiaoming", 24).let { personMan ->
        personMan.name.println()
        personMan.name.println()
    }


    (0L..6L).map(::factorial).forEach(::println)


    var valueStr = listOf("Hello", "Word", "!")


    valueStr.joinToString(separator = ",", prefix = "《", postfix = "》") {
        "$it _"
    }.println()

    valueStr.filter { "Word" == it }.forEach(::print)
    //    valueStr.fold("输入迭代的初始值 ") { acc, str -> "$acc $str" }.forEach(::print)
    valueStr.foldIndexed(StringBuilder()) { index, acc, str ->
        acc.append(index).append(str).append(",")
    }
        .forEach(::print)

    println()

    var valueNum = listOf(1, 4, 5, 6)
    valueNum.reduce { acc, i -> acc + i }.println()
    valueNum.reduce { acc, i ->
        print("$i + ")
        acc + i * 2
    }.println()

    valueStr.takeWhile { s -> "Word" != s }.forEach(::print)
}