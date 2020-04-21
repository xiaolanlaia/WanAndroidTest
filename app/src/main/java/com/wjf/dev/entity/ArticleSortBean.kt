package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/10 11:55
 *
 */


class ArticleSortBean : BaseBean() {

    var data : DataBean? = null


    class DataBean{

        var curPage : Int = 0
        var datas : ArrayList<datasBean>? = null

        class datasBean{
            var id : Int? = 0
            var superChapterId : Int? = 0
            var userId : Int? = 0

            var link : String? = null
            var superChapterName : String? = null
            var niceDate : String? = null
            var shareUser : String? = null
            var author : String? = null
            var title : String? = null
            var origin : String? = null

            var collect : Boolean? = false
            var fresh : Boolean? = false

            var tags : ArrayList<tagsBean>? = null


            class tagsBean{
                var name : String? = null
                var url : String? = null
            }
        }
    }

}
