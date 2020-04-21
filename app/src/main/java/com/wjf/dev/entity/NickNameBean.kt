package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/21 10:52
 *
 */


class NickNameBean : BaseBean() {

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

            var author : String? = null
            var desc : String? = null
            var envelopePic : String? = null
            var link : String? = null
            var niceDate : String? = null
            var shareUser : String? = null
            var superChapterName : String? = null
            var title : String? = null

            var collect : Boolean = false
            var fresh : Boolean = false

            var id : Int? = 0
            var superChapterId : Int? = 0




        }
    }

}