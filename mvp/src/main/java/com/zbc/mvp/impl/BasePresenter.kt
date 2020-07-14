package com.zbc.mvp.impl

import android.os.Bundle
import com.zbc.mvp.IMvpView
import com.zbc.mvp.IPresenter

abstract class BasePresenter<out V : IMvpView<IPresenter<V>>> : IPresenter<V> {

    override var view: V

    override fun onCreate(saveInstanceState: Bundle?) = Unit

    override fun saveInstanceState(outState: Bundle) = Unit

    override fun onViewStateRestored(saveInstanceState: Bundle?) = Unit

    override fun onStart() = Unit

    override fun onResume() = Unit

    override fun onPause() = Unit

    override fun onStop() = Unit

    override fun onDestory() = Unit


}