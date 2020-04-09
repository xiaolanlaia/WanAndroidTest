package com.wjf.dev.main.fragment.home

import com.sun.dev.entity.BaseBean
import com.wjf.dev.entity.*
import com.wjf.dev.util.RetrofitManager
import com.wjf.dev.util.doInBackground
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class HomeRepository {

    /**
     * banner数据
     */
    fun getHomeBannerData() : Observable<HomeBannerBean>{

        return RetrofitManager.getHomeBannerData().doInBackground()
    }
    /**
     * 文章列表
     */
    fun getHomeArticleList() : Observable<HomeArticleBean>{

        return RetrofitManager.getHomeArticleList().doInBackground()
    }
}