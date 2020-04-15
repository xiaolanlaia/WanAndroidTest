package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 13:35
 *
 */


class LoginBean : BaseBean() {

    val data : DataBean? = null

    class DataBean{

        val admin : Boolean? = false

        val id : Int? = 0

        val username : String? = null



    }

}