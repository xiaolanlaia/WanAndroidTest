package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/13 9:09
 *
 */


class OfficialAccountHistoryBean : BaseBean(){

    val data : dataBean? = null
    val offset : Int? = 0
    val pageCount : Int? = 0
    val total : Int? = 0
    val over : Boolean? = false


    class dataBean{

        val curPage : Int? = null
        val datas : ArrayList<datasBean>? = null

        class datasBean{

            val collect : Boolean? = false

            val fresh : Boolean? = false

            val id : Int? = 0
            val superChapterId : Int? = 0
            val type : Int? = 0

            val link : String? = null
            val niceDate : String? = null
            val superChapterName : String? = null
            val title : String? = null
            val author : String? = null

            val tags : ArrayList<tagsBea>? = null


            class tagsBea{
                 val name : String? = null
                val url : String? = null
            }


        }

    }
}