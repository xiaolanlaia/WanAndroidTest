package com.wjf.dev.main.fragment.mine

import com.wjf.dev.entity.BaseBean
import com.wjf.dev.entity.HomeArticleBean
import com.wjf.dev.entity.IntegralBean
import com.wjf.dev.entity.IntegralListBean
import com.wjf.dev.entity.IntegralRankBean
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
    fun getIntegralList() : Observable<IntegralListBean>{

        return RetrofitManager.getIntegralList().doInBackground()

    }
    /**
     * 积分排行榜
     */
    fun getIntegralRank() : Observable<IntegralRankBean>{

        return RetrofitManager.getIntegralRank().doInBackground()

    }

    /**
     * 获取收藏列表
     */
    fun getCollectList() : Observable<HomeArticleBean>{
        return RetrofitManager.getCollectList().doInBackground()
    }

    /**
     * 退出登录
     */
    fun logout() : Observable<BaseBean>{

        return RetrofitManager.logout().doInBackground()
    }


    /**
     * 收藏
     */
    fun addCollect(title : String, author : String, link : String) : Observable<BaseBean>{

        return RetrofitManager.addCollect(title, author, link).doInBackground()
    }
    /**
     * 取消收藏
     */
    fun mineUnCollect(id : Int) : Observable<BaseBean>{

        return RetrofitManager.mineUnCollect(id).doInBackground()
    }
}