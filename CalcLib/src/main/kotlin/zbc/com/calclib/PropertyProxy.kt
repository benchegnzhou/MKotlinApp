package zbc.com.calclib

import kotlin.reflect.KProperty


val a by lazy {
    "123"
}
val b by YProxy()
var c by YProxy()

class Delegete{
    val b by YProxy()
    var c by YProxy()
}

class YProxy {
    var value: String? = null
    operator fun getValue(nothing: Nothing?, property: KProperty<*>): String {
        return value ?: ""
    }

    operator fun setValue(nothing: Nothing?, property: KProperty<*>, any: String) {
        value = any
    }

    operator fun getValue(delegete: Delegete, property: KProperty<*>): String {
        return value ?: ""
    }

    operator fun setValue(delegete: Delegete, property: KProperty<*>, any: String) {
        value = any
    }

}

class XProxy {

    operator fun getValue(nothing: Nothing?, property: KProperty<*>): String {
        return "1231"
    }
}


fun main(args: Array<String>) {
    val delegete = Delegete()

    delegete.b.println()
    delegete.c.println()
    delegete.c="110"
    delegete.b.println()
    delegete.c.println()

}