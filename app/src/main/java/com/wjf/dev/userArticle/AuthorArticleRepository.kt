package com.wjf.dev.userArticle

import com.wjf.dev.entity.BaseBean
import com.wjf.dev.entity.HomeArticleBean
import com.wjf.dev.util.RetrofitManager
import com.wjf.dev.util.doInBackground
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/10 8:59
 *
 */


class AuthorArticleRepository {

    /**
     * 作者文章列表
     */
    fun getAuthorArticleList(AUTHOR_ID : Int) : Observable<HomeArticleBean> {

        return RetrofitManager.getAuthorArticleList(AUTHOR_ID).doInBackground()
    }

    /**
     * 按作者的昵称搜索文章
     */
    fun getAuthorFromNickName(nickName : String) : Observable<HomeArticleBean>{

        return RetrofitManager.getAuthorFromNickName(nickName).doInBackground()
    }

    /**
     * 某个分类下文章
     */
    fun getArticleSort(cid : Int) : Observable<HomeArticleBean>{

        return RetrofitManager.getArticleSort(cid).doInBackground()

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