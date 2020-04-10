package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/10 11:55
 *
 */


class ArticleSortBean : BaseBean() {

    val data : DataBean? = null

    class DataBean{

        val curPage : Int = 0
        val datas : ArrayList<datasBean>? = null

        class datasBean{
            val id : Int? = 0
            val superChapterId : Int? = 0
            val userId : Int? = 0

            val link : String? = null
            val superChapterName : String? = null
            val niceDate : String? = null
            val shareUser : String? = null
            val title : String? = null

            val collect : Boolean? = false
            val fresh : Boolean? = false

            val tags : ArrayList<tagsBean>? = null


            class tagsBean{
                val name : String? = null
                val url : String? = null
            }
        }
    }

}
