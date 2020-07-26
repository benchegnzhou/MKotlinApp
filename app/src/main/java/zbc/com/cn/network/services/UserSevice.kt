package zbc.com.cn.network.services

import io.reactivex.Observable
import retrofit2.http.GET
import zbc.com.cn.network.entities.User
import zbc.com.cn.network.retrofit

/**
 * 分成两个service便于不同的service合起来不好找
 */
interface UserApi {
    @GET("/user")
    fun getAuthenticatedUser(): Observable<User>
}

object UserSevice : UserApi by retrofit.create(UserApi::class.java)