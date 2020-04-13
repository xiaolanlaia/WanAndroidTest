package com.wjf.dev.common

import com.sun.dev.entity.BaseBean
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
    @GET(Constants.URL.GET_HOME_BANNER_DATA)
    fun getHomeBannerData() : Observable<HomeBannerBean>

    /**
     * 文章列表
     */
    @GET(Constants.URL.GET_HOME_ARTICLE_LIST)
    fun getHomeArticleList() : Observable<HomeArticleBean>

    /**
     * 作者文章列表
     */
    @GET("user/{AUTHOR_ID}/share_articles/1/json")
    fun getAuthorArticleList(@Path("AUTHOR_ID") AUTHOR_ID : Int) : Observable<AuthorArticleBean>

    /**
     * 某个分类下文章
     */
    @GET("article/list/0/json")
    fun getArticleSort(@Query("cid") cid : Int) : Observable<ArticleSortBean>

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
    fun getHistoryData(@Path("id")id : Int) : Observable<OfficialAccountHistoryBean>


}