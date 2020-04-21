package com.wjf.dev.userArticle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wjf.dev.entity.ArticleSortBean
import com.wjf.dev.entity.AuthorArticleBean
import com.wjf.dev.entity.NickNameBean
import com.wjf.dev.main.fragment.home.HomeViewModel.Companion.setCollectState
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

    val articleList = MutableLiveData<List<NickNameBean.dataBean.datasBean>>()
    val articleSortList = MutableLiveData<List<ArticleSortBean.DataBean.datasBean>>()

    /**
     * 作者文章列表
     */
//    fun getAuthorArticleList(AUTHOR_ID : Int){
//        repository.getAuthorArticleList(AUTHOR_ID).subscribe({
//            when(it.errorCode){
//
//                0 ->{
//                    articleList.value = it.data!!.shareArticles!!.datas
//                }
//            }
//        },{}).addTo(co)
//    }

    /**
     * 按作者的昵称搜索文章
     */
    fun getAuthorFromNickName(nickName : String){

        repository.getAuthorFromNickName(nickName).subscribe({

            when(it.errorCode){

                0 ->{
                    articleList.value = it.data!!.datas
                }
            }
        },{}).addTo(co)
    }

    /**
     * 获取分类文章
     */
    fun getArticleSort(cid : Int){

        repository.getArticleSort(cid).subscribe({

            when(it.errorCode){

                0 ->{
                    articleSortList.value = it.data!!.datas
                }
            }

        },{}).addTo(co)
    }

    /**
     * 收藏
     */
    fun collect(id : Int){

        repository.collect(id).subscribe({

            when(it.errorCode){

                0 ->{
                    setCollectState.onCollect(true)
                }
            }


        },{

        }).addTo(co)
    }

    /**
     * 取消收藏
     */
    fun unCollect(id : Int){

        repository.unCollect(id).subscribe({

            when(it.errorCode){

                0 ->{
                    setCollectState.onCollect(false)
                }
            }


        },{

        }).addTo(co)
    }
}