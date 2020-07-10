package zbc.com.calclib

import java.lang.reflect.ParameterizedType

interface IView<out P : IPresenter<IView<P>>> {
    val presenter: P
}

interface IPresenter<out V : IView<IPresenter<V>>> {
    val view: V
}

abstract class BaseView<out P : BasePresenter<BaseView<P>>> : IView<P> {
    override val presenter: P

    //涉及到val变量初始化的问题
    init {
        presenter = findPresenterClass().newInstance()
        presenter.view = this
    }

    private fun findPresenterClass(): Class<P> {
        var thisClass: Class<*> = this.javaClass
        while (true) {
            //genericSuperclass相关知识参考 https://blog.csdn.net/bencheng06/article/details/107048444
            (thisClass.genericSuperclass as? ParameterizedType)?.let {
                return it.actualTypeArguments?.firstOrNull() as Class<P>
            } ?: run {
                //如果当前类是MainPresenter的子类，需要获取他的父类，直到找到MainPresenter
                thisClass = thisClass.superclass ?: throw  IllegalArgumentException("没有找到对应的超类")
            }
        }
    }
}

abstract class BasePresenter<out V : IView<BasePresenter<V>>> : IPresenter<V> {
    //告诉编译器我们已经知道不安全
    override lateinit var view: @UnsafeVariance V
}

class MainView : BaseView<MainPresenter>() {

}


class MainPresenter : BasePresenter<MainView>() {

}


fun main(args: Array<String>) {
    MainView().presenter.let { println(it) }


    //协变的例子,查看源码List<out E> 是一个协变相当于 List<? extends T>，传出参数是Number ， 传入参数类型是泛型子类即可
    val numberList: List<*> = arrayListOf(1, 2, 5, 6)


    //逆变的例子,查看Comparable源码是一个逆变函数Comparable（in T）,相当于Comparable<? super T>
    val intComparable: Comparable<*> = object : Comparable<Any> {
        override fun compareTo(other: Any): Int {
            return 0
        }
    }


    //不变的例子，
    val numArrayList: MutableList<Any> = mutableListOf(1, 2, 4, 5)

    //总结，协变（继承关系）顺着来，逆变（继承关系）逆着来，不变（继承关系）不相往来
}