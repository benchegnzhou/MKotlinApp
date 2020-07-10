package zbc.com.calclib

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.regex.Pattern

fun main(args: Array<String>) {
    var samInJava = SamInJava()
    val lambda = { println("我是添加进来的代码块") }
    samInJava.addTask(lambda)
    samInJava.addTask(lambda)
    samInJava.addTask(lambda)
    samInJava.addTask(lambda)

    samInJava.removeTask(lambda)
    samInJava.removeTask(lambda)
    samInJava.removeTask(lambda)
    samInJava.removeTask(lambda)


    val source = "Hello , my phone number is 010-82628118."
    val pattern = """.*(\d{3}-\d{8}).*"""
//    val matcher = Pattern.compile(pattern).matcher(source)

    Regex(pattern).findAll(source).toList().flatMap(MatchResult::groupValues).forEach(::println)


//    while (matcher.find()) {
//        matcher.group().println()
//        matcher.group(1).println()
//    }


    val listOf = listOf("早", "上", "好", "！")
    val map = mapOf("1" to "早", "2" to "上", "3" to "好", "4" to "！")
    readFile()
}


fun readFile() {
    val file = File("build.gradle")
    val bufferedReader = BufferedReader(FileReader(file))
    bufferedReader.readLines().forEach(::println)
}

