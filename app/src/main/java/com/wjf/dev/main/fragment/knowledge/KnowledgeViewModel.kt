package com.wjf.dev.main.fragment.knowledge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wjf.dev.entity.KnowledgeBean
import com.wjf.dev.main.fragment.knowledge.KnowledgeRepository
import com.wjf.dev.util.addTo
import io.reactivex.disposables.CompositeDisposable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:12
 *
 */


class KnowledgeViewModel(val repository: KnowledgeRepository) : ViewModel() {

    val co = CompositeDisposable()
    val dataList = MutableLiveData<List<KnowledgeBean.DataBean>>()

    /**
     * 获取体系列表数据
     */
    fun getSystemDataList(){
        repository.getSystemDataList().subscribe({

            when(it.errorCode){

                0 ->{
                    dataList.value = it.data
                }
            }


        },{

        }).addTo(co)
    }
}