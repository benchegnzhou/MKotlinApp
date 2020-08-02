package zbc.com.cn.modle

import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.Android
import retrofit2.HttpException
import zbc.com.cn.network.entities.AuthorizationReq
import zbc.com.cn.network.entities.AuthorizationRsp
import zbc.com.cn.network.entities.User
import zbc.com.cn.network.services.AuthorService
import zbc.com.cn.utils.fromJson
import zbc.com.cn.utils.pref

import kotlin.Exception

object AccountManager {
    var authId by pref(-1)
    var userName by pref("")
    var password by pref("")
    var token by pref("")

    //登录成功，用户信息存储
    private var userJson by pref("")


    private var currentUser: User?
        get() {
            if (field == null && userJson.isNotEmpty()) {
                field =Gson().fromJson<User>(userJson)
            }
            return field
        }

    fun isLogedIn(): Boolean = token.isNotEmpty()


    fun login() = AuthorService.createAuthorization(AuthorizationReq())
        .observeOn(AndroidSchedulers.mainThread())
        //定义程序执行在IO线程
        .subscribeOn(Schedulers.io())
        .doOnNext {
            //执行完成没有token
            if (it.token.isEmpty()) {
                throw AccountException(it)
            }
        }

    fun logout() = AuthorService.deleteAuthorization(authId)
        .doOnNext {
            if (it.isSuccessful) {
                //退出登录成功，清空本地用户信息
                authId = -1
                token = ""
            } else {
                throw HttpException(it)
            }
        }


    class AccountException(val authorizationRsp: AuthorizationRsp) :
        Exception("Already Logged in !")

}

