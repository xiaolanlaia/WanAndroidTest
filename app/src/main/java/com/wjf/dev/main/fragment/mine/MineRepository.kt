package com.wjf.dev.main.fragment.mine

import com.wjf.dev.entity.IntegralBean
import com.wjf.dev.util.RetrofitManager
import com.wjf.dev.util.doInBackground
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class MineRepository {

    /**
     * 获取积分
     */
    fun getIntegral() : Observable<IntegralBean>{
        return RetrofitManager.getIntegral().doInBackground()

    }
    /**
     * 积分列表
     */
    fun getIntegralList(){

    }
    /**
     * 积分排行榜
     */
    fun getIntegralRank(){

    }

    /**
     * 收藏列表
     */
    fun getCollectList(){

    }
}