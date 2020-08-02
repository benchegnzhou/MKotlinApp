package zbc.com.cn.network.services

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path
import zbc.com.cn.network.entities.AuthorizationReq
import zbc.com.cn.network.entities.AuthorizationRsp
import zbc.com.cn.network.retrofit
import zbc.com.cn.settings.Configs


interface AuthorApi {

    @PUT("/authorizations/clients/${Configs.Account.clientId}/{fingerprint}")
    fun createAuthorization(
        @Body req: AuthorizationReq,
        @Path("fingerprint") fingerprint: String = Configs.Account.fingerprint
    ): Observable<AuthorizationRsp>

    @DELETE("/authorizations/{id}")
    fun deleteAuthorization(@Path("id") id: Int): Observable<Response<Any>>
}


object AuthorService : AuthorApi by retrofit.create(AuthorApi::class.java)