package com.wjf.dev.common

import com.wjf.dev.entity.BaseBean
import com.wjf.dev.entity.*
import io.reactivex.Observable
import retrofit2.http.*

/**
 *       Created by xiaolanlaia on 2019/5/6 13:40
 */
interface ApiService {


    /**
     * banner数据
     */
    @GET("banner/json")
    fun getHomeBannerData() : Observable<HomeBannerBean>

    /**
     * 文章列表
     */
    @GET("article/list/0/json")
    fun getHomeArticleList() : Observable<ArticleBean>

    /**
     * 广场列表
     */
    @GET("user_article/list/0/json")
    fun getSquareList() : Observable<ArticleBean>

    /**
     * 最新项目
     */
    @GET("article/listproject/0/json")
    fun getLatestProject() : Observable<ArticleBean>

    /**
     * 收藏
     */
    @POST("lg/collect/{id}/json")
    fun collect(@Path("id") id : Int) : Observable<BaseBean>
    /**
     * 取消收藏
     */
    @POST("lg/uncollect_originId/{id}/json")
    fun unCollect(@Path("id") id : Int) : Observable<BaseBean>
    /**
     * 取消收藏
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    fun mineUnCollect(@Path("id") id : Int,
                      @Field("originId")originId : Int) : Observable<BaseBean>

    /**
     * 作者文章列表
     */
    @GET("user/{AUTHOR_ID}/share_articles/1/json")
    fun getAuthorArticleList(@Path("AUTHOR_ID") AUTHOR_ID : Int) : Observable<ArticleBean>

    /**
     * 按作者的昵称搜索文章
     */
    @GET("/article/list/0/json")
    fun getAuthorFromNickName(@Query("author")nickName : String) : Observable<ArticleBean>

    /**
     * 某个分类下文章
     */
    @GET("article/list/0/json")
    fun getArticleSort(@Query("cid") cid : Int) : Observable<ArticleBean>

    /**
     * 获取体系列表数据
     */
    @GET("tree/json")
    fun getSystemDataList() : Observable<KnowledgeBean>

    /**
     * 获取公众号列表
     */
    @GET("wxarticle/chapters/json")
    fun getOfficialAccountList() : Observable<OfficialAccountBean>

    /**
     * 公众号历史数据
     */
    @GET("wxarticle/list/{id}/1/json")
    fun getHistoryData(@Path("id")id : Int) : Observable<ArticleBean>

    /**
     * 导航数据
     */
    @GET("navi/json")
    fun getNavigationData() : Observable<NavArticleBean>


    /**
     * 获取积分
     */
    @GET("lg/coin/userinfo/json")
    fun getIntegral() : Observable<IntegralBean>

    /**
     * 积分列表
     */
    @GET("lg/coin/list/1/json")
    fun getIntegralList() : Observable<IntegralListBean>

    /**
     * 积分排行榜
     */
    @GET("coin/rank/1/json")
    fun getIntegralRank() : Observable<IntegralRankBean>

    /**
     * 登录
     */
    @POST("user/login")
    @FormUrlEncoded
    fun loginIn(@Field("username")username : String,
                @Field("password")password : String) : Observable<LoginBean>

    /**
     * 注册
     */
    @POST("user/register")
    @FormUrlEncoded
    fun loginUp(@Field("username")username : String,
                @Field("password")password : String,
                @Field("repassword")repassword : String) :Observable<BaseBean>

    /**
     * 退出登录
     */
    @GET("user/logout/json")
    fun loginOut() : Observable<BaseBean>

    /**
     * 获取收藏列表
     */
    @GET("lg/collect/list/0/json")
    fun getCollectList() : Observable<ArticleBean>

    /**
     * 退出登录
     */
    @GET("user/logout/json")
    fun logout() : Observable<BaseBean>


    /**
     * 收藏
     */
    @POST("/lg/collect/add/json")
    @FormUrlEncoded
    fun addCollect(@Field("title")title : String,
                   @Field("author")author : String,
                   @Field("link")link : String) : Observable<BaseBean>


}