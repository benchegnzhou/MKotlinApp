package com.zbc.mvp.impl

import androidx.fragment.app.Fragment
import com.zbc.mvp.IMvpView

abstract class BaseFrament<out P : BasePresenter<BaseFrament<P>>> : IMvpView<P>, Fragment() {

    override val presenter: P


    init {
        presenter = createPresenterKt()
        presenter.view = this
    }

    fun createPresenterKt(): P {

    }


}