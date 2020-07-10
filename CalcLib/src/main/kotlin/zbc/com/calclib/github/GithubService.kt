package zbc.com.calclib.github

import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("https://github.com/Sunzxyong/Recovery/starGazers")
    fun getStarGazers(@Query("page") page: Int = 1, @Query("per_page") pageSize: Int = 20)
}

//https://blog.csdn.net/heng615975867/article/details/80355090 dagger2介绍
object Service{

}