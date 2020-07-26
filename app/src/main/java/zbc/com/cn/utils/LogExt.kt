package zbc.com.cn.utils

import android.os.Build
import android.preference.Preference
import androidx.annotation.RequiresApi
import com.orhanobut.logger.Logger
import zbc.com.cn.application.AppContext


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline fun <reified R, T> R.pref(default: T) = Preference(AppContext, null, 0, R::class.jvmName)