package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 16:44
 *
 */


class HomeBannerBean : BaseBean() {

    var data : ArrayList<BannerData>? = null


    class BannerData{

        var desc : String? = null
        var id : Int? = 0
        var imagePath : String? = null
        var isVisible : Int? =0
        var order : Int? = 0
        var title : String? = null
        var type : Int? = 0
        var url : String? = null
    }
}


