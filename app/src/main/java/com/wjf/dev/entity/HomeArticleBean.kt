package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/9 8:30
 *
 */


class HomeArticleBean : BaseBean() {

    var data : Data? = null

    class Data{

        var curPage : Int? = 0

        var offset : Int? = 0
        var size : Int? = 0
        var total : Int? = 0
        var pageCount : Int? = 0

        var over : Boolean? = false
        var datas : ArrayList<Datas>? = null


        class Datas{
            var shareUser : String? = null
            var title : String? = null
            var superChapterName : String? = null
            var niceDate : String? = null
            var origin : String? = null
            var link : String? = null
            var fresh : Boolean? = false
            var collect : Boolean? = false
            var top : Boolean? = false
            var id : Int? = 0
            var userId : Int? = 0
            var superChapterId : Int? = 0

        }
    }

}
