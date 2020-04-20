package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/17 12:18
 *
 */


class ProjectBean : BaseBean() {

    var data : dataBean? = null



    class dataBean{

        var offset : Int? = 0
        var pageCount : Int? = 0
        var size : Int? = 0
        var total : Int? = 0

        var over : Boolean? = false

        var datas : ArrayList<datasBean>? = null

        class datasBean{
            var author : String? = null
            var chapterName : String? = null
            var desc : String? = null
            var envelopePic : String? = null
            var link : String? = null
            var niceDate : String? = null
            var superChapterName : String? = null
            var title : String? = null
            var origin : String? = null

            var collect : Boolean? = false
            var fresh : Boolean? = false

            var id : Int? = 0
            var superChapterId : Int? = 0

            var tags : ArrayList<tagsBean>? = null

            class tagsBean{

                var name : String? = null
                var url : String? = null
            }
        }




    }
}