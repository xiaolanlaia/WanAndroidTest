package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 16:44
 *
 */


class HomeBannerBean : BaseBean() {

    val data : ArrayList<BannerData>? = null


    class BannerData{

        val desc : String? = null
        val id : Int? = 0
        val imagePath : String? = null
        val isVisible : Int? =0
        val order : Int? = 0
        val title : String? = null
        val type : Int? = 0
        val url : String? = null
    }
}


