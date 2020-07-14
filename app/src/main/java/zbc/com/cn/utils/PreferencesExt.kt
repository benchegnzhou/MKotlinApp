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
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {

        putPreference(name, value)

    }

    private fun <T> findPreference(name: String, defultValue: T): T {
        return with(prefs) {
            when (defultValue) {
                is Int -> getInt(name, defultValue)
                is Long -> getLong(name, defultValue)
                is Float -> getFloat(name, defultValue)
                is String -> getString(name, defultValue)
                is Boolean -> getBoolean(name, defultValue)
                else -> throw IllegalArgumentException("不受支持的类型 ${value}")
            }
        } as T
    }

    private fun putPreference(name: String, value: T) {
        with(prefs.edit()) {
            when (value) {
                is Int -> putInt(name, value)
                is Long -> putLong(name, value)
                is Float -> putFloat(name, value)
                is String -> putString(name, value)
                is Boolean -> putBoolean(name, value)
                else -> throw IllegalArgumentException("不受支持的类型 ${value}")
            }
        }.commit()

    }


}

