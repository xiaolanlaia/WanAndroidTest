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
    /**
     * 广场列表
     */
    fun getSquareList() : Observable<HomeArticleBean>{

        return RetrofitManager.getSquareList().doInBackground()
    }

    /**
     * 最新项目
     */
    fun getLatestProject() : Observable<ProjectBean>{

        return RetrofitManager.getLatestProject().doInBackground()
    }

    /**
     * 收藏
     */
    fun collect(id : Int) : Observable<BaseBean>{

        return RetrofitManager.collect(id).doInBackground()
    }
    /**
     * 取消收藏
     */
    fun unCollect(id : Int) : Observable<BaseBean>{

        return RetrofitManager.unCollect(id).doInBackground()
    }


}