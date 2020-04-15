package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 10:18
 *
 */


class IntegralBean : BaseBean(){

    val data : dataBean? = null

    class dataBean{

        val coinCount :Int? = 0
        val level :Int? = 0
        val rank :Int? = 0
        val username :String? = null
    }
}