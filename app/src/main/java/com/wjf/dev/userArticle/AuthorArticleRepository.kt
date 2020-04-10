package com.wjf.dev.userArticle

import com.wjf.dev.entity.ArticleSortBean
import com.wjf.dev.entity.AuthorArticleBean
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
    fun getAuthorArticleList(AUTHOR_ID : Int) : Observable<AuthorArticleBean> {

        return RetrofitManager.getAuthorArticleList(AUTHOR_ID).doInBackground()
    }

    /**
     * 某个分类下文章
     */
    fun getArticleSort(cid : Int) : Observable<ArticleSortBean>{

        return RetrofitManager.getArticleSort(cid).doInBackground()

    }
}