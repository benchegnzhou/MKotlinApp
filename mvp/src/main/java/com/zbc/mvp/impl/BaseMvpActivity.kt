package com.zbc.mvp.impl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zbc.mvp.IMvpView
import kotlin.reflect.KClass
import kotlin.reflect.KType
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
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun saveInstanceState(outState: Bundle) {

    }

    override fun onViewStateRestored(saveInstanceState: Bundle?) {

    }


    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestory() {
//        super.onDestory()
    }


    /**
     * 创建  Presenter
     */
    fun createPresenterKt(): P {

        sequence {
            var thisClass: KClass<*> = this@BaseMvpActivity::class
            while (true) {
                yield(thisClass.supertypes)
                //找到thisClass的所有父类，supertypes是包含接口的
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure ?: break
            }
        }.flatMap {
            it.flatMap {
                //获取到泛型参数
                it.arguments
            }
        }.first{
            it.
        }
        return
    }
}