package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/16 9:16
 *
 */


class IntegralRankBean : BaseBean() {

    val data : dataBean? = null

    class dataBean{
        val curPage : Int? = 0
        val offset : Int? = 0
        val pageCount : Int? = 0
        val size : Int? = 0
        val total : Int? = 0

        val over : Boolean? = false

        val datas : ArrayList<datasBean>? = null

        class datasBean{

            val coinCount : Int? = 0
            val level : Int? = 0
            val rank : Int? = 0
            val userId : Int? = 0

            val username : String? = null
        }
    }
}