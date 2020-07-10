package zbc.com.cn

import androidx.core.text.isDigitsOnly
import java.lang.Exception
import java.util.regex.Pattern


fun main(args: Array<String>) {
    while (true) {
        print("欢迎使用迷你计算器，请输入需要计算的公式 ，如 1+2 ")
        print("\n\n\n")
        var line = readLine()


        if (!Pattern.matches("[0-9+\\-*/\\s]+", line) || line == null) {
            print("请输入合法的算式表达式,例如 1+2")

        } else {
            try {
                val toList = line.asSequence()
                    .filter { it.toString().isNotBlank() }
                    .toList()

                calc(toList)
            } catch (e: Exception) {
                print("${e.message}\n")
            }
        }

        print("输入n退出，任意键继续")
        print("\n\n\n")


        if (readLine().toString().trim().toLowerCase() == "n") {
            return
        }

    }
}

fun calc(line: List<Char>) {
    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    var result = ""
    var sb = StringBuilder()
    var fistNum = ""
    var operator = ""
    for ((index, value) in line.withIndex()) {
        print("$value \n")
        if (value in '0'..'9') {
            sb.append(value)
            if (index == line.size - 1) {
                println(getResult(fistNum, operator, sb.toString()))
                sb.delete(0, sb.length - 1)
            }
        } else {
            if (operator.isNotEmpty() && fistNum.isEmpty()) throw IllegalArgumentException("非法的表达式")
            if (operator.isEmpty()) {
                fistNum = sb.toString()
                sb.clear()
                operator = value.toString()
            } else {
                result = getResult(fistNum, operator, sb.toString())
                println(result)
                sb.clear()
            }
        }
    }


}

fun getResult(fistNum: String, operator: String, lastNum: String): String {
    var a = if (fistNum.toString().isEmpty()) {
        0.0
    } else {
        fistNum.toDouble()
    }
    var b = if (lastNum.toString().isEmpty()) {
        0.0
    } else {
        lastNum.toDouble()
    }


    return when (operator) {
        "+" -> "$a + $b = ${a + b}"
        "-" -> "$a - $b = ${a - b}"
        "*" -> "$a * $b = ${a * b}"
        "/" -> "$a / $b = ${a / b}"
        else -> throw Exception("求值错误")
    }

}
