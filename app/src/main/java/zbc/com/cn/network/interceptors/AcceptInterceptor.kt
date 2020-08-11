package zbc.com.cn.network.interceptors

import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response

class AcceptInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()

        Logger.d("zbc_okhttp:  ",origin.url().toString())
        Logger.d("zbc_okhttp:  ",origin.headers().toString())
        Logger.d("zbc_okhttp:  ",origin.body().toString())
        Logger.d("zbc_okhttp:  ",origin.isHttps().toString())
        return chain.proceed(
            origin.newBuilder()
                .apply {
                    header(
                        "accept",
                        "application/vnd.github.v3.full+json, ${origin.header("accept") ?: ""}"
                    )
                }.build()
        )
    }
}