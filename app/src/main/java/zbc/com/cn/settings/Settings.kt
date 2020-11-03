package zbc.com.cn.settings

import zbc.com.cn.R
import zbc.com.cn.application.AppContext
import zbc.com.cn.modle.AccountManager
import zbc.com.cn.utils.pref


object Settings {
    var lastPage: Int
        get() = if(lastPageIdString.isEmpty()) 0 else AppContext.resources.getIdentifier(lastPageIdString, "id", AppContext.packageName)
        set(value) {
            lastPageIdString = AppContext.resources.getResourceEntryName(value)
        }

    val defaultPage
        get() = if(AccountManager.isLogedIn()) defaultPageForUser else defaultPageForVisitor

    private var defaultPageForUser by pref(R.id.navRepos)

    private var defaultPageForVisitor by pref(R.id.navRepos)

    private var lastPageIdString by pref("")

    var themeMode by pref("DAY")
}