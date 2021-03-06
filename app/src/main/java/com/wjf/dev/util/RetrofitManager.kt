package com.wjf.dev.util

import com.wjf.dev.entity.BaseBean
import com.wjf.dev.common.ApiService
import com.wjf.dev.common.Constants
import com.wjf.dev.common.cookie.CookieManager
import com.wjf.dev.entity.*
import io.reactivex.Observable
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *       Created by xiaolanlaia on 2019/5/6 13:39
 */
object RetrofitManager {


    /**
     * 设置okhttp
     */
    var okHttp = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder()
//            request.addHeader("x-client-token", SharedHelper.getShared().getString("token", ""))
            val response = it.proceed(request.build())

            response
        }

        .addInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .cookieJar(CookieManager.getInstance())

    val apiService = getRetrofit().create(ApiService::class.java)


    /**
     * 获取retrofit
     */
    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .client(okHttp.build())
            .baseUrl(Constants.URL.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * 封装RequestBody
     */
    fun getRequestBody(str: String): RequestBody {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), str)
    }




    /**
     * banner数据
     */
    fun getHomeBannerData() : Observable<HomeBannerBean>{

        return apiService.getHomeBannerData()
    }

    /**
     * 文章列表
     */
    fun getHomeArticleList() : Observable<ArticleBean>{

        return apiService.getHomeArticleList()
    }

    /**
     * 广场列表
     */
    fun getSquareList() : Observable<ArticleBean>{

        return apiService.getSquareList()
    }

    /**
     * 最新项目
     */
    fun getLatestProject() : Observable<ArticleBean>{

        return apiService.getLatestProject()
    }

    /**
     * 收藏
     */
    fun collect(id : Int) : Observable<BaseBean>{

        return apiService.collect(id)
    }
    /**
     * 取消收藏
     */
    fun unCollect(id : Int) : Observable<BaseBean>{

        return apiService.unCollect(id)
    }
    /**
     * 取消收藏
     */
    fun mineUnCollect(id : Int) : Observable<BaseBean>{

        return apiService.mineUnCollect(id, -1)
    }

    /**
     * 作者文章列表
     */
    fun getAuthorArticleList(AUTHOR_ID : Int) : Observable<ArticleBean> {

        return apiService.getAuthorArticleList(AUTHOR_ID)
    }

    /**
     * 按作者的昵称搜索文章
     */
    fun getAuthorFromNickName(nickName : String) : Observable<ArticleBean>{

        return apiService.getAuthorFromNickName(nickName)
    }

    /**
     * 某个分类下文章
     */
    fun getArticleSort(cid : Int) : Observable<ArticleBean>{

        return apiService.getArticleSort(cid)

    }

    /**
     * 获取体系列表数据
     */
    fun getSystemDataList() : Observable<KnowledgeBean>{

        return apiService.getSystemDataList()
    }

    /**
     * 获取公众号列表
     */
    fun getOfficialAccountList() : Observable<OfficialAccountBean>{
        return apiService.getOfficialAccountList()
    }

    /**
     * 公众号历史数据
     */
    fun getHistoryData(id : Int) : Observable<ArticleBean>{
        return apiService.getHistoryData(id)
    }

    /**
     * 导航数据
     */
    fun getNavigationData() : Observable<NavArticleBean>{

        return apiService.getNavigationData()
    }

    /**
     * 获取积分
     */
    fun getIntegral() : Observable<IntegralBean>{
        return apiService.getIntegral()

    }
    /**
     * 积分列表
     */
    fun getIntegralList() : Observable<IntegralListBean>{

        return apiService.getIntegralList()

    }

    /**
     * 积分排行榜
     */
    fun getIntegralRank() : Observable<IntegralRankBean>{

        return apiService.getIntegralRank()

    }

    /**
     * 登录
     */
    fun loginIn(username : String,password : String) : Observable<LoginBean>{

        return apiService.loginIn(username,password)
    }

    /**
     * 注册
     */
    fun loginUp(username : String,password : String,repassword : String) :Observable<BaseBean>{


        return apiService.loginUp(username,password,repassword)
    }

    /**
     * 退出登录
     */
    fun loginOut() : Observable<BaseBean>{
        return apiService.loginOut()
    }

    /**
     * 获取收藏列表
     */
    fun getCollectList() : Observable<ArticleBean>{
        return apiService.getCollectList()
    }

    /**
     * 退出登录
     */
    fun logout() : Observable<BaseBean>{

        return apiService.logout()
    }


    /**
     * 收藏
     */
    fun addCollect(title : String, author : String, link : String) : Observable<BaseBean>{

        return apiService.addCollect(title, author, link)
    }




}