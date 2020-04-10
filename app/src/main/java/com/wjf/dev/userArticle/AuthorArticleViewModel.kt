package com.wjf.dev.userArticle

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wjf.dev.entity.ArticleSortBean
import com.wjf.dev.entity.AuthorArticleBean
import com.wjf.dev.util.addTo
import io.reactivex.disposables.CompositeDisposable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/10 8:59
 *
 */


class AuthorArticleViewModel(val repository: AuthorArticleRepository) : ViewModel() {

    val co = CompositeDisposable()

    val articleList = MutableLiveData<List<AuthorArticleBean.DataBean.ShareArticlesBean.DatasBean>>()
    val articleSortList = MutableLiveData<List<ArticleSortBean.DataBean.datasBean>>()

    /**
     * 作者文章列表
     */
    fun getAuthorArticleList(AUTHOR_ID : Int){
        repository.getAuthorArticleList(AUTHOR_ID).subscribe({
            when(it.errorCode){

                0 ->{
                    articleList.value = it.data!!.shareArticles!!.datas
                }
            }
        },{}).addTo(co)
    }

    /**
     * 获取分类文章
     */
    fun getArticleSort(cid : Int){
        Log.d("__cid-","${cid}")

        repository.getArticleSort(cid).subscribe({

            when(it.errorCode){

                0 ->{
                    articleSortList.value = it.data!!.datas
                }
            }

        },{}).addTo(co)
    }
}