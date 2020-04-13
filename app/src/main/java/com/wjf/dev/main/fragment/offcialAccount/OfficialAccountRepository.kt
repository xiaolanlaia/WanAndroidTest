package com.wjf.dev.main.fragment.offcialAccount

import com.wjf.dev.entity.OfficialAccountBean
import com.wjf.dev.entity.OfficialAccountHistoryBean
import com.wjf.dev.util.RetrofitManager
import com.wjf.dev.util.doInBackground
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class OfficialAccountRepository {
    /**
     * 获取公众号列表
     */
    fun getOfficialAccountList() : Observable<OfficialAccountBean>{
        return RetrofitManager.getOfficialAccountList().doInBackground()
    }

    /**
     * 公众号历史数据
     */
    fun getHistoryData(id : Int) : Observable<OfficialAccountHistoryBean>{
        return RetrofitManager.getHistoryData(id).doInBackground()
    }
}