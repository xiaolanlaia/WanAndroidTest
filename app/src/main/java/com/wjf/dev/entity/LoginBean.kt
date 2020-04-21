package com.wjf.dev.entity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 13:35
 *
 */


class LoginBean : BaseBean() {

    var data : DataBean? = null

    class DataBean{

        var admin : Boolean? = false

        var id : Int? = 0

        var username : String? = null



    }

}