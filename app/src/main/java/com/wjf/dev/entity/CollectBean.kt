package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 16:56
 *
 */


class CollectBean : BaseBean() {

    var data : dataBean? = null

    class dataBean{


        var curPage : Int? = 0
        var offset : Int? = 0
        var pageCount : Int? = 0
        var size : Int? = 0
        var total : Int? = 0


        var over : Boolean? = null

        var datas : ArrayList<datasBean>? = null

        class datasBean{

            var author : String? = null
            var chapterName : String? = null
            var link : String? = null
            var niceDate : String? = null
            var title : String? = null
            var origin : String? = null
            var id : Int? = 0
            var chapterId : Int? = 0
            var collect : Boolean? = true

        }

    }
}