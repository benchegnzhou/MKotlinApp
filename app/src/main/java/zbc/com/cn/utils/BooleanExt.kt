package zbc.com.cn.utils



sealed class BooleanExt<out T>

object OtherWise : BooleanExt<Nothing>()

class withData<T>(val data: T) : BooleanExt<T>()

fun <T> BooleanExt<T>.otherwise(block: () -> T): T {
    when (this) {
        is OtherWise -> {
            return block()
        }
        is withData -> {
            return this.data
        }
    }
}


//https://blog.csdn.net/Jaden_hool/article/details/78437947
inline fun <T> Boolean.yes(block: () -> T): BooleanExt<T> {
    when {
        this -> {
            return withData(block())
        }
        else -> {
            return OtherWise
        }
    }
}

fun main(args: Array<String>) {
    val lambda = {
        print("OK")
        1
    }

    val lambda2 = {
        print("NOT OK !")
        2
    }
    val a = true.yes(lambda).otherwise(lambda2)
    print(a)
}

