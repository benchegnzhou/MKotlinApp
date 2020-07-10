package zbc.com.calclib

import java.util.function.Function

//闭包
fun justCount(age: Int): () -> Unit {
    var count = 0
    return {
        println("当前的数据为 ${++count} age_$age")
    }
}

fun fibonacci(): () -> Long {
    var first = 0L
    var second = 1L
    return fun(): Long {
        var result = second
        second += first
        first = second - first
        return result
    }
}

fun fibonacci2(): Iterable<Long> {
    var first = 0L
    var second = 1L

    return Iterable {
        object : LongIterator() {
            override fun hasNext(): Boolean {
                return true
            }

            override fun nextLong(): Long {
                var result = second
                second += first
                first = second - first
                return result
            }
        }
    }
}


val add5 = { x: Int -> x + 5 }
val multiplyBy2 = { x: Int -> x * 2 }

//
//infix fun <P1, P2, R> Function1<P2, R>.andThen(function: Function1<P1, P2>): Function1<P1, R> {
//    return fun(p1: P1): R {
//        return this.invoke(function.invoke(p1))
//    }
//}

infix fun <P1, P2, R> Function1<P2, R>.andThen(function: Function1<P1, P2>): Function1<P1, R> {
    return fun(p1: P1): R {
        return this.invoke(function.invoke(p1))
    }
}

//f(x)
val funFx = { i: Int -> i + 2 }

//g(x,y)
val funGxy = { i: Int, j: Int -> 3 * i + 100 / j }

infix fun <P1, P2, P3, R> Function1<P3, R>.complexFun(function: Function2<P1, P2, P3>): Function2<P1, P2, R> {
    return fun(p1, p2): R {
        return this.invoke(function.invoke(p1, p2))
    }
}

infix fun <P1, P2, P3, R> Function2<P2, P3, R>.complexFun2(function: Function1<P1, P2>): Function2<P1, P3, R> {
    return fun(p1, p3): R {
        println("p1=$p1,p3=$p3")
        return this.invoke(function.invoke(p1),p3)
    }
}


fun main(args: Array<String>) {
//    var aa = funFx complexFun funGxy
//    aa(3, 5).println()

    var b = funGxy complexFun2 funFx
    b(2, 3).println()

   /* var ccc = multiplyBy2 andThen add5
    print(ccc(5))
    multiplyBy2(add5(5)).println()*/
}






















