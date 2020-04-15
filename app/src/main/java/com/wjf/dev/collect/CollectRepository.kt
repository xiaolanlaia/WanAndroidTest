package com.wjf.dev.collect

import com.wjf.dev.entity.CollectBean
import com.wjf.dev.util.RetrofitManager
import com.wjf.dev.util.doInBackground
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 16:44
 *
 */


class CollectRepository {
    /**
     * 获取收藏列表
     */
    fun getCollectList() : Observable<CollectBean>{
        return RetrofitManager.getCollectList().doInBackground()
    }
}