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

}