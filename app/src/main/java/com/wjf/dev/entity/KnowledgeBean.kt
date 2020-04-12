package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/11 8:45
 *
 */


class KnowledgeBean : BaseBean(){

    val data : ArrayList<DataBean>? = null

    class DataBean{

        val children : ArrayList<childrenBean>? = null
        val courseId : Int? = 0
        val id : Int? = 0
        val order : Int? = 0
        val parentChapterId : Int? = 0
        val visible : Int? = 0

        val name : String? = null

        val userControlSetTop : Boolean? = false

        class childrenBean{

            val children : ArrayList<String>? = null
            val courseId : Int? = 0
            val id : Int? = 0
            val order : Int? = 0
            val parentChapterId : Int? = 0
            val visible : Int? = 0

            val name : String? = null

            val userControlSetTop : Boolean? = false

        }

    }
}
