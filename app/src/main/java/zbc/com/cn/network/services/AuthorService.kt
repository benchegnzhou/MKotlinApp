package zbc.com.cn.network.services

import retrofit2.http.PUT
import zbc.com.cn.settings.Configs

interface AuthorApi {

    @PUT("/authorizations/clients/${Configs.Account.clientId}")
    fun createAuthorization()
}