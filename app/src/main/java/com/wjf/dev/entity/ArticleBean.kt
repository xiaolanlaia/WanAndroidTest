package com.wjf.dev.entity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/9 8:30
 *
 */


class ArticleBean : BaseBean() {

    var data : DataBean? = null

    class DataBean{

        var curPage : Int? = 0

        var offset : Int? = 0
        var size : Int? = 0
        var total : Int? = 0
        var pageCount : Int? = 0

        var over : Boolean? = false
        var datas : ArrayList<DatasBean>? = null


        class DatasBean{
            var shareUser : String? = null
            var author : String? = null
            var title : String? = null
            var superChapterName : String? = null
            var chapterName : String? = null
            var niceDate : String? = null
            var envelopePic : String? = null
            var origin : String? = null
            var desc : String? = null
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
