package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/9 8:30
 *
 */


class HomeArticleBean : BaseBean() {

    val data : Data? = null

    class Data{

        val curPage : Int? = 0

        val offset : Int? = 0
        val size : Int? = 0
        val total : Int? = 0
        val pageCount : Int? = 0

        val over : Boolean? = false
        val datas : ArrayList<Datas>? = null


        class Datas{
            val shareUser : String? = null
            val title : String? = null
            val superChapterName : String? = null
            val niceDate : String? = null
            val link : String? = null
            val fresh : Boolean? = false
            val collect : Boolean? = false
            val top : Boolean? = false
            val id : Int? = 0
            val userId : Int? = 0
            val superChapterId : Int? = 0

        }
    }

}
