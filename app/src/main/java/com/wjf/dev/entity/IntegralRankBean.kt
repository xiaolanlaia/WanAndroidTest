package com.wjf.dev.entity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/16 9:16
 *
 */


class IntegralRankBean : BaseBean() {

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
            var level : Int? = 0
            var rank : Int? = 0
            var userId : Int? = 0

            var username : String? = null
        }
    }
}