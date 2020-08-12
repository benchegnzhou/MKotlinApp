package zbc.com.cn.network

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import zbc.com.cn.application.AppContext
import zbc.com.cn.interceptors.AuthInterceptor
import zbc.com.cn.network.interceptors.AcceptInterceptor
import zbc.com.cn.utils.ensureDir
import java.io.File
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.github.com"

/**
 * 创建缓存文件夹
 */
private val cacheFile by lazy {
    File(AppContext.cacheDir, "webSeriveApi").apply { ensureDir() }
}


/**
 * 通用网络请求创建
 */
val retrofit by lazy {
    Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        //拷贝开源项目源码，包名创建相同可以完成对引用项目的扩展,以后的网络请求都不切换线程了
        .addCallAdapterFactory(
            RxJava2CallAdapterFactory.createWithSchedulers(
                Schedulers.io(),
                AndroidSchedulers.mainThread()
            )
        )
        .client(
            OkHttpClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(Cache(cacheFile, 1024 * 1024 * 1024))
                .addInterceptor(AuthInterceptor())
                .addInterceptor(AcceptInterceptor())
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build()
        )
        .baseUrl(BASE_URL)
        .build()

}

