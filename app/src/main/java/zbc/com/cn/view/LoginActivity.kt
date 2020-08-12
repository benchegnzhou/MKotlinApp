package zbc.com.cn.view

import android.os.Bundle
import android.view.View
import com.zbc.mvp.impl.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import zbc.com.cn.R
import zbc.com.cn.network.entities.User
import zbc.com.cn.presenter.LoginPresenter
import java.lang.IllegalArgumentException

class LoginActivity : BaseMvpActivity<LoginPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signInButton.setOnClickListener {
            presenter.doLogin(username.text.toString(), password.text.toString())
        }
    }


    fun showTips(view: View, msg: String) {
        toast(msg)
    }


    fun loginStart() {
        loginProgress.visibility=View.VISIBLE
    }

    fun loginFinish() {
        loginProgress.visibility=View.GONE
    }

    fun logninSuccess(user: User) {
        toast("登录成功")
    }

    fun loginFail(throws: Throwable) {
        when {
            throws is IllegalArgumentException -> toast(throws.message.toString())
            else -> toast("登录失败，网络错误")
        }

    }
}