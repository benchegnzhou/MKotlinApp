package com.zbc.mvp.impl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zbc.mvp.IMvpView
import com.zbc.mvp.IPresenter
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure


/**
 * Created by benchengzhou on 2020/7/17  17:13 .
 * 作者邮箱： mappstore@163.com
 * 功能描述： 基础类
 * 类    名：
 * 备    注：
 */

abstract class BaseMvpActivity<out P : BasePresenter<BaseMvpActivity<P>>> : IMvpView<P>,
    AppCompatActivity() {

    override val presenter: P


    init {
        presenter = createPresenterKt()
        presenter.view = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }


    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


    /**
     * 创建  Presenter
     */
    fun createPresenterKt(): P {

        sequence {
            var thisClass: KClass<*> = this@BaseMvpActivity::class
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
}