package zbc.com.cn.presenter

import com.zbc.mvp.impl.BasePresenter
import zbc.com.cn.modle.AccountManager
import zbc.com.cn.modle.onAccountStatesChangeListener
import zbc.com.cn.network.entities.User
import zbc.com.cn.utils.otherwise
import zbc.com.cn.utils.yes
import zbc.com.cn.view.activity.LoginActivity
import java.lang.IllegalArgumentException

class LoginPresenter : BasePresenter<LoginActivity>(), onAccountStatesChangeListener {

    init {
        AccountManager.onAccountStatesChangeListenerList.add(this)
    }


    fun doLogin(userName: String, password: String) {
        AccountManager.userName = userName
        AccountManager.password = password
        view.loginStart()
        checkUserName(userName)
            .yes {
                checkPassword(password)
                    .yes {
                        AccountManager
                            .login()
                            .subscribe({
                                view.loginFinish()

                            }, {
                                view.loginFinish()
                                //登录失败
                                view.loginFail(it)
                            })

                    }
                    .otherwise {
                        view.loginFinish()
                        view.loginFail(
                            throws = throw IllegalArgumentException(
                                "密码不符合规范"
                            )
                        )
                    }
            }
            .otherwise {
                view.loginFinish()
                view.loginFail(
                    throws = throw IllegalArgumentException(
                        "用户名不符合规范"
                    )
                )
            }
    }


    fun checkUserName(userName: String): Boolean {
        return true
    }


    fun checkPassword(password: String): Boolean {
        return true
    }

    override fun onLogin(user: User) {
        //登录成功
        view.logninSuccess(user)
    }

    override fun onLogout() {

    }
}