package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/12 11:06
 *
 */


class OfficialAccountBean : BaseBean(){

    val data : ArrayList<dataBean>? = null


    class dataBean{

        val id : Int? = 0
        val order : Int? = 0
        val parentChapterId : Int? = 0
        val visible : Int? = 0

        val name : String? = null

        val userControlSetTop : Boolean? = false

        val children : ArrayList<childrenBean>? = null

        class childrenBean{}

    }
}

