package com.wjf.dev.entity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/13 10:23
 *
 */


class NavArticleBean : BaseBean(){
    var data : ArrayList<dataBean>? = null


    class dataBean{
        var cid : Int? = 0
        var name : String? = null

        var articles : ArrayList<articlesBean>? = null

        class articlesBean{

            var author : String? = null
            var chapterName : String? = null
            var link : String? = null
            var niceDate : String? = null
            var title : String? = null
            var origin : String? = null


            var id : Int? = null
            var chapterId : Int? = null

            var collect : Boolean? = false
            var fresh : Boolean? = false

        }
    }
}