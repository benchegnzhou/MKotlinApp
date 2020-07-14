package zbc.com.cn.application

import android.app.Application
import android.content.ContextWrapper


private lateinit var INSTANCE: Application

class MApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}


object AppContext : ContextWrapper(INSTANCE)