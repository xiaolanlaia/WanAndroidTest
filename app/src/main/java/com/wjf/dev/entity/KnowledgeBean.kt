package com.wjf.dev.entity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/11 8:45
 *
 */


class KnowledgeBean : BaseBean(){

    var data : ArrayList<DataBean>? = null

    class DataBean{

        var children : ArrayList<childrenBean>? = null
        var courseId : Int? = 0
        var id : Int? = 0
        var order : Int? = 0
        var parentChapterId : Int? = 0
        var visible : Int? = 0

        var name : String? = null

        var userControlSetTop : Boolean? = false

        class childrenBean{

            var children : ArrayList<String>? = null
            var courseId : Int? = 0
            var id : Int? = 0
            var order : Int? = 0
            var parentChapterId : Int? = 0
            var visible : Int? = 0

            var name : String? = null

            var userControlSetTop : Boolean? = false

        }

    }
}
