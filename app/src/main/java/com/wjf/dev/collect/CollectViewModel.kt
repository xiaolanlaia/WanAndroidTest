package com.wjf.dev.collect

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wjf.dev.entity.CollectBean
import com.wjf.dev.util.addTo
import io.reactivex.disposables.CompositeDisposable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 16:43
 *
 */


class CollectViewModel(val repository: CollectRepository) : ViewModel() {

    val co = CompositeDisposable()

    val collectList = MutableLiveData<List<CollectBean.dataBean.datasBean>>()

    /**
     * 获取收藏列表
     */
    fun getCollectList(){

        repository.getCollectList().subscribe({

            collectList.value = it.data!!.datas
        },{

        }).addTo(co)
    }
}