package zbc.com.cn

fun main(args: Array<String>) {
    demo4()
}

/**
 * 可以有返回值，表达式的最后一行代码作为返回值
 */
fun demo1() {
    var a = 5

    var c = when (a) {
        10 -> {
            10
        }
        20 -> {
            20
        }
        else -> {
            0
        }
    }

    System.out.println(c)
}


/**
 * 多个分支复用一个代码块，可以使用 ‘,’分隔
 */
fun demo2() {
    var a = 5

    var c = when (a) {
        1, 2, 5, 10 -> {
            10
        }
        15, 62, 20 -> {
            20
        }
        else -> {
            0
        }
    }

    System.out.println(c)
}


/**
 * 多个连续的范围复用一个代码块，可以使用 in关键字链接一个函数范围
 */
fun demo3() {
    var a = 5

    var c = when (a) {
        in 1..10 -> {
            10
        }
        in 20..30 -> {
            20
        }
        else -> {
            0
        }
    }

    System.out.println(c)
}


/**
 * 可以在条件中使用函数
 */
fun demo4() {
    var a = 25

    var c = when (a) {

        getA(5) -> {
            10
        }
        getA(2 * a) -> {
            20
        }
        else -> {
            0
        }
    }

    System.out.println(c)
}

fun getA(a: Int): Int {
    return a * a
}




