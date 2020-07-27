package zbc.com.cn.utils

import com.zbc.commonutils.sharepreference.PreferencesExt
import zbc.com.cn.application.AppContext
import kotlin.reflect.jvm.jvmName

/**
 * Created by benny on 6/23/17.
 */
inline fun <reified R, T> R.pref(default: T) = PreferencesExt(AppContext, "", default, R::class.jvmName)