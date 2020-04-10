package com.wjf.dev.main.fragment.home

import android.content.Context
import android.util.Log
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
    val articleList = MutableLiveData<List<HomeArticleBean.Data.Datas>>()

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



}