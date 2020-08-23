package zbc.com.cn.application

import android.app.Application
import android.content.ContextWrapper
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.zzhoujay.richtext.RichText


private lateinit var INSTANCE: Application

class MApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        Logger.addLogAdapter(AndroidLogAdapter())
        richTextInit()
    }


    fun richTextInit() {
        RichText.initCacheDir(this);
        RichText.debugMode = true;
    }
}


object AppContext : ContextWrapper(INSTANCE)