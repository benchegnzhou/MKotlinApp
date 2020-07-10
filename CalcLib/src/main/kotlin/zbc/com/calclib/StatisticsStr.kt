package zbc.com.calclib

import java.io.File


fun getAllStr() {
    val text = File("build.gradle").readText()
    val map = HashMap<Char, Int>()
   /* text.toCharArray().filterNot { it.isWhitespace() }.forEach {
        if (map[it] == null) map.put(it, 1)
        else map[it] = 1 + map[it]!!
    }*/
    text.toCharArray().filterNot { it.isWhitespace() }.groupBy {
        it
    }.map { it.key to it.value.size }.forEach(::println)
}


fun main(args: Array<String>) {
    getAllStr()
}