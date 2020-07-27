package zbc.com.cn.utils

import zbc.com.cn.application.AppContext

/**
 * Created by benchengzhou on 2020/7/17  17:12 .
 * 作者邮箱： mappstore@163.com
 * 功能描述： 
 * 类    名：
 * 备    注： 
 */

object  UserMsg {
    var userName: String by com.zbc.commonutils.sharepreference.PreferencesExt(
        AppContext,
        "userName",
        "",
        "",
        "user_Info"
    )
    var password: Long by com.zbc.commonutils.sharepreference.PreferencesExt(
        AppContext,
        "password",
        0,
        0,
        "user_Info"
    )
    var visiable: Boolean by com.zbc.commonutils.sharepreference.PreferencesExt(
        AppContext,
        "isVisable",
        false,
        false,
        "user_Info"
    )

}