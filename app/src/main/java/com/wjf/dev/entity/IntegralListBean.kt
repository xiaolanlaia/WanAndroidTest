package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/16 8:39
 *
 */


class IntegralListBean : BaseBean(){

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
            val id : Int? = 0

            val desc : String? = null
            val reason : String? = null

        }

    }
}
