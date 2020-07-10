@file:JvmName("Demo")
package zbc.com.calclib.reflections

import zbc.com.calclib.annotations.Poko
import zbc.com.calclib.println
import kotlin.reflect.full.memberExtensionFunctions
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor


class Person(val name: String, val age: Int)


@Poko
class Person2(val name: String, val age: Int) {
    override
    fun toString(): String {
        return "{name=${name},age=${age}}"
    }
}


class Person3(val name: String, val age: Int) {
    fun getPersonName(): String {
        return name
    }

    fun playTogether(firendName: String, firendAge: Int): String {
        return "朋友 ${firendName} ,  ${firendAge} 岁时和 ${name} ,${age} 岁时经常一起交玩耍"
    }

    override
    fun toString(): String {
        return "{name=${name},age=${age}}"
    }
}

fun Person3.sayHello(personName: String) {
    println("Hello ${personName}")
}

@Poko
class Person4 {
    var name = ""
    var age = 0

    //重写无参构造器，虚拟机将不会为这个类生成无参构造器
    constructor()
    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }

    override
    fun toString(): String {
        return "{name=${name},age=${age}}"
    }

    fun String.name() {
        println("String,name")
    }
}

fun Person4.Hello() {
    println("Person4.Hello")
}


data class Person5(var name: String, var age: Int) {

    operator fun plus(p: Person5) = Person5(name + p.name, age + p.age)

    override
    fun toString(): String {
        return "{name=${name},age=${age}}"
    }

    fun String.name() {
        println("String,name")
    }
}


fun personSayYes(personName: String) {
    println("${personName} say YES ")
}


class NoPrimary()
class defaultConstructor()

fun main(args: Array<String>) {
    //1. 获取KClass第一种方式，;kotlin类直接获取KClass
    val kClass1 = Person::class
    val xiaoming = Person("小明", 12)
    //2. 获取KClass第二种方式，;实例对象直接获取KClass
    val kClass2 = xiaoming::class
    //3. 获取KClass第三种方式，;实例对象.javaClass.kotlin
    val kClass3 = xiaoming.javaClass.kotlin
    println("___")
    //通过KClass获取默认构造器，调用构造器的实例方法call
    val xiaowan = Person2::class.primaryConstructor?.call("xiaowan", 21)
    println(xiaowan)
    //看一个没有默认构造器的示例
    val zhenzhen = Person4::class.primaryConstructor?.call("zhenzhen", 21)
    println(zhenzhen)

    Person4::class.constructors.forEach { println(it) }

    val xiaorui = Person4::class.constructors.first().call()
    println(xiaorui.toString())
    val Leeli = Person4::class.constructors.last().call("Leeli", 24)
    println(Leeli.toString())

//    Person4::class.memberProperties.forEach(::println)
    // get(receiver: T): R 存在写变和逆变，无法直接调用
    // Person4::class.memberProperties.first{it.name=="name"}.get(Person4::class)
//    Person4::class.memberProperties.first{it.name=="name"}.get(Leeli).let { println("name=${it}") }
//    Person4::class.memberProperties.first{it.name=="age"}.get(Leeli).let { println("age=${it}")  }
    Person4::class.memberFunctions.forEach(::println)

    println("获取类内部扩展方法")
    Person4::class.memberExtensionFunctions.forEach(::println)
    println("获取类注解")
    Person4::class.annotations.forEach(::println)
    println("获取成员注解")
    Person4::class.memberProperties.first { it.name == "name" }.annotations.forEach(::println)

    print(Int.MAX_VALUE)


}