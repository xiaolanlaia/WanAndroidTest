package com.wjf.dev.main.fragment.navigation

import com.wjf.dev.entity.NavArticleBean
import com.wjf.dev.util.RetrofitManager
import com.wjf.dev.util.doInBackground
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/13 10:37
 *
 */


class NavigationRepository {
    /**
     * 导航数据
     */
    fun getNavigationData() : Observable<NavArticleBean>{

        return RetrofitManager.getNavigationData().doInBackground()
    }
}