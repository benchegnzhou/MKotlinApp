package zbc.com.cn.utils

import zbc.com.cn.application.AppContext


object  UserMsg {
    var userName: String by PreferencesExt(AppContext,"userName","","","user_Info")
    var password: Long by PreferencesExt(AppContext,"password",0,0,"user_Info")
    var visiable: Boolean by PreferencesExt(AppContext,"isVisable",false,false,"user_Info")

}