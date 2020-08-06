package zbc.com.cn.presenter

import android.widget.Toast
import com.zbc.mvp.impl.BasePresenter
import zbc.com.cn.application.AppContext
import zbc.com.cn.modle.AccountManager
import zbc.com.cn.utils.otherwise
import zbc.com.cn.utils.yes
import zbc.com.cn.view.LoginActivity

class LoginPresenter : BasePresenter<LoginActivity>() {


    fun doLogin(userName: String, password: String) {
        AccountManager.userName = userName
        AccountManager.password = password

        checkUserName(userName)
            .yes {
                checkPassword(password)
                    .yes {
                        AccountManager
                            .login()
                            .subscribe({
                                //登录成功
                                view.showTips(view., "用户名错误")
                            }, {
                                //登录失败
                                Toast.makeText(AppContext, "登录失败", Toast.LENGTH_SHORT)
                            })

                    }
                    .otherwise {
                        view.showTips(view., "密码错误")
                    }
            }
            .otherwise {

            }
    }


    fun checkUserName(userName: String): Boolean {
        return true
    }


    fun checkPassword(password: String): Boolean {
        return true
    }
}