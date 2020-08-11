package zbc.com.cn.settings


import com.orhanobut.logger.Logger
import zbc.com.cn.application.AppContext
import zbc.com.cn.utils.getDeviceId


object Configs {
    object Account {
        val SCOPES = listOf<String>("user", "repo", "notifications", "gist", "admin:org")
        const val clientId = "00a7cf9bb78308fa861e"
        const val clientSecret = "89ac4d8972cc03f376bbf289685ef35752bdf88b"
        const val note = ""
        const val noteUrl = ""

        val fingerprint by lazy {
            (AppContext.getDeviceId + clientId).apply { Logger.d(this); }
        }
    }
}