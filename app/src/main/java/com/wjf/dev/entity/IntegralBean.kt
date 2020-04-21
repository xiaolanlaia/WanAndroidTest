package com.wjf.dev.entity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 10:18
 *
 */


class IntegralBean : BaseBean(){

    var data : dataBean? = null

    class dataBean{

        var coinCount :Int? = 0
        var level :Int? = 0
        var rank :Int? = 0
        var username :String? = null
    }
}