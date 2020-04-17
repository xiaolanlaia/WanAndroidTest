package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/17 12:18
 *
 */


class ProjectBean : BaseBean() {

    val data : dataBean? = null



    class dataBean{

        val offset : Int? = 0
        val pageCount : Int? = 0
        val size : Int? = 0
        val total : Int? = 0

        val over : Boolean? = false

        val datas : ArrayList<datasBean>? = null

        class datasBean{
            val author : String? = null
            val chapterName : String? = null
            val desc : String? = null
            val envelopePic : String? = null
            val link : String? = null
            val niceDate : String? = null
            val superChapterName : String? = null
            val title : String? = null

            val collect : Boolean? = false
            val fresh : Boolean? = false

            val id : Int? = 0
            val superChapterId : Int? = 0

            val tags : ArrayList<tagsBean>? = null

            class tagsBean{

                val name : String? = null
                val url : String? = null
            }
        }




    }
}