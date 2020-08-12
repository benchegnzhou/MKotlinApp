package zbc.com.cn.modle

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.Android
import retrofit2.HttpException
import zbc.com.cn.network.entities.AuthorizationReq
import zbc.com.cn.network.entities.AuthorizationRsp
import zbc.com.cn.network.entities.User
import zbc.com.cn.network.services.AuthorService
import zbc.com.cn.network.services.UserSevice
import zbc.com.cn.utils.fromJson
import zbc.com.cn.utils.pref

import kotlin.Exception

interface onAccountStatesChangeListener {

    fun onLogin(user: User)

    fun onLogout()
}


object AccountManager {
    var authId by pref(-1)
    var userName by pref("")
    var password by pref("")
    var token by pref("")

    //登录成功，用户信息存储
    private var userJson by pref("")


    private var currentUser: User? = null
        get() {
            if (field == null && userJson.isNotEmpty()) {
                field = Gson().fromJson<User>(userJson)
            }
            return field
        }
        set(value) {
            //清空当前用户或者赋值给当前用户
            if (value == null) {
                userJson = ""
                field = null
            } else {
                userJson = Gson().toJson(value)
                field = value
            }
        }


    fun isLogedIn(): Boolean = token.isNotEmpty()


    fun login() = AuthorService.createAuthorization(AuthorizationReq())
        .doOnNext {
            //执行完成没有token，说明上次已经登录但是没有执行登出操作
            if (it.token.isEmpty()) {
                //可以主动抛出自定义异常，方便自己捕获然后处理
                throw AccountException(it)
            }
        }
        //当条件成立的时候重试
        .retryWhen {
            it.flatMap {
                //捕获到登录重复异常，先执行登出操作,这里可以用于捕获异常自己操作
                if (it is AccountException) {
                    //先执行登出操作
                    AuthorService.deleteAuthorization(it.authorizationRsp.id)
                        .doOnNext {
                            if (it.isSuccessful) {
                                //退出登录成功，清空本地用户信息
                                authId = -1
                                token = ""
                                currentUser = null
                            }
                        }
                } else {
                    Observable.error(it)
                }
            }
        }
        .flatMap {
            //执行登录操作成功，用户信息存储本地
            authId = it.id
            token = it.token
            UserSevice.getAuthenticatedUser()
        }
        .map {
            currentUser = it
            //登录成功调用回调接口
            notityLogin(it)
        }
        //放在墙面只用第一次变换有效，放在最后表示全部请求都有效
        .observeOn(AndroidSchedulers.mainThread())
        //定义程序执行在IO线程
        .subscribeOn(Schedulers.io())

    fun logout() = AuthorService.deleteAuthorization(authId)
        .doOnNext {
            if (it.isSuccessful) {
                //退出登录成功，清空本地用户信息
                authId = -1
                token = ""
                currentUser = null
                //登出成功调用回调接口
                notifyLogout()
            } else {
                throw HttpException(it)
            }
        } //放在墙面只用第一次变换有效，放在最后表示全部请求都有效
        .observeOn(AndroidSchedulers.mainThread())
        //定义程序执行在IO线程
        .subscribeOn(Schedulers.io())


    class AccountException(val authorizationRsp: AuthorizationRsp) :
        Exception("Already Logged in !")


    val onAccountStatesChangeListenerList = ArrayList<onAccountStatesChangeListener>()

    private fun notityLogin(user: User) {
        //防止线程并发，遍历中list修改会报错
        (onAccountStatesChangeListenerList.clone() as ArrayList<onAccountStatesChangeListener>)
            .forEach {
                it.onLogin(user)
            }
    }

    private fun notifyLogout() {
        //防止线程并发，遍历中list修改会报错
        (onAccountStatesChangeListenerList.clone() as ArrayList<onAccountStatesChangeListener>)
            .forEach {
                it.onLogout()
            }
    }

}

