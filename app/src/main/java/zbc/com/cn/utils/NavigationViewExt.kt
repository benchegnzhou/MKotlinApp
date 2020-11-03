package zbc.com.cn.utils

import android.view.View
import androidx.core.view.ViewCompat
import com.google.android.material.navigation.NavigationView

/**
 * https://www.jianshu.com/p/51a7b9e9596b
 */
inline fun NavigationView.doOnLayoutAvailable(crossinline block: () -> Unit) {
    //isLaidOut 可以判断控件是否是测量完成
    ViewCompat.isLaidOut(this).yes {
        block()
    }.otherwise {
        this.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View?,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {

                removeOnLayoutChangeListener(this)
                block()
            }
        })

    }
}