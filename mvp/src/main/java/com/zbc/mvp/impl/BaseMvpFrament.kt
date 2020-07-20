package com.zbc.mvp.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zbc.mvp.ILiveCycle
import com.zbc.mvp.IMvpView
import com.zbc.mvp.IPresenter
import java.lang.Thread.yield
import java.lang.reflect.ParameterizedType

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

abstract class BaseFragment<out P : BasePresenter<BaseFragment<P>>> : IMvpView<P>, Fragment() {

    override val presenter: P


    init {
        presenter = createPresenterKt()
        //在fragment初始化的地方对view初始化
        presenter.view = this
    }


    /**
     * 使用反射的形式获取P的类型
     */
    fun createPresenterKt(): P {
        sequence {
            var thisClass: KClass<*> = this@BaseFragment::class
            while (true) {
                //https://www.jianshu.com/p/9f720b9ccdea?utm_source=desktop&utm_medium=timeline
                //https://blog.csdn.net/weixin_34306593/article/details/89691830
                //yield是一个suspend方法, 放弃执行权, 并将数据返回.
                yield(thisClass.supertypes)
                //找到thisClass的所有父类，supertypes是包含接口的
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure ?: break
            }
        }.flatMap {
            it.flatMap {
                //获取到泛型参数
                it.arguments
            }.asSequence()
        }.first {
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class) ?: false
        }.let {
            return it.type!!.jvmErasure.primaryConstructor!!.call() as P
        }

    }


    /**
     * java版本创建presenter
     */
    fun createPresenter(): P {
        return sequence {
            var thisClass: Class<*> = this@BaseFragment::class.java

            while (true) {
                yield(thisClass.genericSuperclass)
                thisClass = thisClass.superclass ?: break
            }
        }.filter {
            it is ParameterizedType
        }.flatMap {
            (it as ParameterizedType).actualTypeArguments.asSequence()
        }.first {
            it is Class<*> && IPresenter::class.java.isAssignableFrom(it)
        }.let {
            return (it as Class<P>).newInstance()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        presenter.onViewStateRestored(savedInstanceState)
    }



    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }
}



class MainFragment:BaseFragment<MainPresenter>()

class MainPresenter:BasePresenter<MainFragment>()