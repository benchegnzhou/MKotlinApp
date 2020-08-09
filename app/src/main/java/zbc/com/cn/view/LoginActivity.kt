package zbc.com.cn.view

import android.os.Bundle
import android.view.View
import com.zbc.mvp.impl.BaseMvpActivity
import org.jetbrains.anko.toast
import zbc.com.cn.R
import zbc.com.cn.network.entities.User
import zbc.com.cn.presenter.LoginPresenter
import java.lang.IllegalArgumentException

class LoginActivity : BaseMvpActivity<LoginPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    override fun onViewStateRestored(saveInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }


    fun showTips(view: View, msg: String) {

    }


    fun loginStart() {

    }

    fun loginFinish() {

    }

    fun logninSuccess(user: User) {

    }

    fun loginFail(throws: Throwable) {
        when {
            throws is IllegalArgumentException -> toast(throws.message.toString())
            else ->  toast("登录失败，网络错误")
        }

    }
}