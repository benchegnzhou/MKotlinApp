package zbc.com.calclib

object UserManager {

    fun getUserName(): String {
        return "小明"
    }
}

fun Any.println() = println(this)


class Student {
    object Family {
        var num = 12
        fun openDoor() {
            println("open the door")
        }
    }
}

class User {
    companion object UserInfo {
        var age = 12
        fun getAge() {
            println("user age is $age")
        }
    }
}


class ClassA {
    private val a = object {
        fun method() {
            println("priave")
        }
    }
    val b = object {
        fun method() {
            println("priave")
        }
    }

    private fun c() = object {
        fun method() {
            println("method")
        }
    }

    fun invok() {
        val o = object {
            fun method() {
                println("inner")
            }
        }
        a.method()
        c().method()
        o.method()

    }
}


class Overload {
    fun a(): Int {
        return 0
    }

    fun a(str: String): Int {
        return str.length
    }
}

// fun Any.println()=println(this)


fun main(args: Array<String>) {
    Overload().a()


}