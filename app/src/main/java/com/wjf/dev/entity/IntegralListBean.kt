package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/16 8:39
 *
 */


class IntegralListBean : BaseBean(){

    var data : dataBean? = null

    class dataBean{

        var curPage : Int? = 0
        var offset : Int? = 0
        var pageCount : Int? = 0
        var size : Int? = 0
        var total : Int? = 0


        var over : Boolean? = false

        var datas : ArrayList<datasBean>? = null

        class datasBean{

            var coinCount : Int? = 0
            var id : Int? = 0

            var desc : String? = null
            var reason : String? = null

        }

    }
}
