package zbc.com.cn.modle

import zbc.com.cn.utils.pref

object AccountManager {
    var authId by pref(-1)
    var userName by pref("")
    var password by pref("")
    var token by pref("")

    fun isLogin() {

    }
}