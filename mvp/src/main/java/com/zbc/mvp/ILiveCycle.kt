package com.zbc.mvp

import android.os.Bundle

interface ILiveCycle {

    fun onCreate(saveInstanceState: Bundle?)

    fun saveInstanceState(outState: Bundle)

    fun onViewStateRestored(saveInstanceState: Bundle?)


    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestory()


}