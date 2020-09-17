package zbc.com.cn.list

import org.junit.Assert

fun main(args: Array<String>) {

    var list = LinkedList<String>()
    list.add("xiaoming")
    list.add("wangming")
    list.add("liming")
    list.add("damign")
    list.add("lixian")

    System.out.println("wangming  "+list.get(0))
//    list.clear()

//    Assert.assertEquals("wangming",list.get(0))
    System.out.println(list)
}