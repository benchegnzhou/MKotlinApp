package zbc.com.cn

fun main(args: Array<String>) {
    testIfAsExpression()
    testIfAsExpression2()
}

/**
 * 作为表达式使用可以有返回值
 * 返回最后一行表达式的值
 */
fun testIfAsExpression() {
    var a = 10
    var b = 20

    var c = if (a > b) {
        a = a + b
        //返回最后一行表达式的值
        a * 2
    } else {
        b = b - a
        //返回最后一行表达式的值
        b * 2
    }

    System.out.println(c)

}

/**
 * 替换java中的三目运算符
 */
fun testIfAsExpression2() {
    var a = true
    var c = if (a) 5 else 6
    System.out.println(c)
}