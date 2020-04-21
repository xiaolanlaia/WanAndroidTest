package com.wjf.dev.entity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/12 11:06
 *
 */


class OfficialAccountBean : BaseBean(){

    var data : ArrayList<dataBean>? = null


    class dataBean{

        var id : Int? = 0
        var order : Int? = 0
        var parentChapterId : Int? = 0
        var visible : Int? = 0

        var name : String? = null

        var userControlSetTop : Boolean? = false

        var children : ArrayList<childrenBean>? = null

        class childrenBean{}

    }
}

