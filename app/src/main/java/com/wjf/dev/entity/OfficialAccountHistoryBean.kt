package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/13 9:09
 *
 */


class OfficialAccountHistoryBean : BaseBean(){

    var data : dataBean? = null
    var offset : Int? = 0
    var pageCount : Int? = 0
    var total : Int? = 0
    var over : Boolean? = false


    class dataBean{

        var curPage : Int? = null
        var datas : ArrayList<datasBean>? = null

        class datasBean{

            var collect : Boolean? = false

            var fresh : Boolean? = false

            var id : Int? = 0
            var superChapterId : Int? = 0
            var type : Int? = 0

            var link : String? = null
            var niceDate : String? = null
            var origin : String? = null
            var superChapterName : String? = null
            var title : String? = null
            var author : String? = null

            var tags : ArrayList<tagsBea>? = null


            class tagsBea{
                var name : String? = null
                var url : String? = null
            }


        }

    }
}