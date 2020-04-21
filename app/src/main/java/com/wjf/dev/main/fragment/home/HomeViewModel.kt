package com.wjf.dev.main.fragment.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wjf.dev.common.Constants
import com.wjf.dev.entity.HomeArticleBean
import com.wjf.dev.entity.HomeBannerBean
import com.wjf.dev.util.CodeUtil.checkIsLogin
import com.wjf.dev.util.addTo
import com.wjf.dev.util.toast
import io.reactivex.disposables.CompositeDisposable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:12
 *
 */


class HomeViewModel(val repository: HomeRepository) : ViewModel() {

    val co = CompositeDisposable()

    val bannerData = MutableLiveData<List<HomeBannerBean.BannerData>>()
    val articleList = MutableLiveData<List<HomeArticleBean.DataBean.DatasBean>>()
    val projectArticleList = MutableLiveData<List<HomeArticleBean.DataBean.DatasBean>>()

    fun getHomeBannerData(context: Context){

        repository.getHomeBannerData().subscribe({

            when(it.errorCode){

                0 ->{

                    bannerData.value = it.data
                }

                Constants.DATA.LOGIN_FAIL ->{

                    checkIsLogin(context)
                }

                else ->{

                    toast(it.errorMsg)
                }
            }
        },{

        }).addTo(co)
    }

    /**
     * 首页文章列表
     */
    fun getHomeArticleList(){

        repository.getHomeArticleList().subscribe({

            when(it.errorCode){

                0 ->{
                    articleList.value = it.data!!.datas
                }
            }

        },{

        }).addTo(co)
    }

    /**
     * 广场列表
     */
    fun getSquareList(){

        repository.getSquareList().subscribe({

            when(it.errorCode){

                0 ->{
                    articleList.value = it.data!!.datas
                }

                else ->{}

            }

        },{

        }).addTo(co)
    }

    /**
     * 最新项目
     */
    fun getLatestProject(){

        repository.getLatestProject().subscribe({

            when(it.errorCode){

                0 ->{

                    projectArticleList.value = it.data!!.datas
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

    companion object{
        lateinit var setCollectState : SetCollectState

        fun collectListener(setCollectState: SetCollectState){

            this.setCollectState = setCollectState
        }

        interface SetCollectState{

            fun onCollect(isCollect : Boolean)
        }
    }





}