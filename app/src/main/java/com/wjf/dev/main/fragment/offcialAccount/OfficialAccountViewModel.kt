package com.wjf.dev.main.fragment.offcialAccount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wjf.dev.entity.OfficialAccountBean
import com.wjf.dev.entity.OfficialAccountHistoryBean
import com.wjf.dev.main.fragment.project.ProjectRepository
import com.wjf.dev.util.addTo
import io.reactivex.disposables.CompositeDisposable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:12
 *
 */


class OfficialAccountViewModel(val repository: OfficialAccountRepository) : ViewModel() {

    val co = CompositeDisposable()
    val articleList = MutableLiveData<List<OfficialAccountBean.dataBean>>()
    val historyList = MutableLiveData<List<OfficialAccountHistoryBean.dataBean.datasBean>>()

    /**
     * 获取公众号列表
     */
    fun getOfficialAccountList(){

        repository.getOfficialAccountList().subscribe({
            when(it.errorCode){

                0 ->{
                    articleList.value = it.data

                }
            }


        },{

        }).addTo(co)
    }

    /**
     * 公众号历史数据
     */
    fun getHistoryData(id : Int){

        repository.getHistoryData(id).subscribe({
            when(it.errorCode){

                0 ->{
                    historyList.value = it.data!!.datas

                }
            }


        },{}).addTo(co)
    }
}