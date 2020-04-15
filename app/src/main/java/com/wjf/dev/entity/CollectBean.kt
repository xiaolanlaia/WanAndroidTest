package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 16:56
 *
 */


class CollectBean : BaseBean() {

    val data : dataBean? = null

    class dataBean{


        val curPage : Int? = 0
        val offset : Int? = 0
        val pageCount : Int? = 0
        val size : Int? = 0
        val total : Int? = 0


        val over : Boolean? = null

        val datas : ArrayList<datasBean>? = null

        class datasBean{

            val author : String? = null
            val chapterName : String? = null
            val link : String? = null
            val niceDate : String? = null
            val title : String? = null

            val id : Int? = 0
            val chapterId : Int? = 0

        }

    }
}