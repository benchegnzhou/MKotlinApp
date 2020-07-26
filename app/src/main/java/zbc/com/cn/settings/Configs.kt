package zbc.com.cn.settings

import zbc.com.cn.application.AppContext
import zbc.com.cn.utils.getDeviceId

object Configs {
    object Account {
        val SCOPES = listOf<String>("user","repo","notifications","gist","admin:org")
        const val clientId=""
        const val clientSecret=""
        const val note=""
        const val noteUrl=""

        val fingPrint by lazy {
            (AppContext.getDeviceId+ clientId).apply { logg }
        }
    }
}