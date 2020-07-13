package zbc.com.cn.utils

import android.content.Context
import java.lang.IllegalArgumentException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


class PreferencesExt<T>(
    var context: Context, var name: String, var value: T,
    var default: T, var prfName: String = "zbc_sp"
) : ReadWriteProperty<Any, T> {
    private val prefs by lazy {
        context.getSharedPreferences("zbc_user_info", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {

    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {

        putPreference(name, value)

    }

    private fun putPreference(name: String, value: T) {
        when (value) {
            is Int -> prefs.edit().putInt(name, value)
            is Long -> prefs.edit().putLong(name, value)
            is Float -> prefs.edit().putFloat(name, value)
            is String -> prefs.edit().putString(name, value)
            is Boolean -> prefs.edit().putBoolean(name, value)
            else -> throw IllegalArgumentException("不受支持的类型 ${value}")
        }
    }


}


fun main(args: Array<String>) {


}