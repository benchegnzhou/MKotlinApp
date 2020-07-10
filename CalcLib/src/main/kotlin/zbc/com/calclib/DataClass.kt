package zbc.com.calclib

import zbc.com.calclib.annotations.Poko

@Poko
data class Person(var name :String,var age :Int)


fun main(args: Array<String>) {
    Person("xiao ming",20).println()
}