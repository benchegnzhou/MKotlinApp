package zbc.com.cn.interceptors

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response
import zbc.com.cn.modle.AccountManager

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val orginal = chain.request()
        return chain.proceed(
            orginal
                .newBuilder()
                .apply {
                    when {
                        orginal.url().pathSegments().contains("authorizations") -> {
                            val userCredentials =
                                "${AccountManager.userName}:${AccountManager.password}"
                            //注意base64出来有空白字符，需要去除前后空白字符
                            val auth = "Basic " + String(
                                Base64.encode(
                                    userCredentials.toByteArray(),
                                    Base64.DEFAULT
                                )
                            ).trim()

                            header("Authorization", auth)
                        }
                        AccountManager.isLogedIn() -> {
                            val token = "Token " + AccountManager.token
                            header("Token", token)
                        }

                        else -> {
                            removeHeader("Authorization")
                        }

                    }
                }.build()
        )

    }

}