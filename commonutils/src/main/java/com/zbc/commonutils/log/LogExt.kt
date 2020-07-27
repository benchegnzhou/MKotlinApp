package com.zbc.commonutils.log

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


object LogExt {
    fun LoggerInit() {
        Logger.addLogAdapter(AndroidLogAdapter());
    }
//    Logger.d("debug");
//    Logger.e("error");
//    Logger.w("warning");
//    Logger.v("verbose");
//    Logger.i("information");
//    Logger.wtf("What a Terrible Failure");
}
