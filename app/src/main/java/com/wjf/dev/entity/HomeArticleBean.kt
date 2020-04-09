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
        val datas : ArrayList<Datas>? = null

        class Datas{
            val shareUser : String? = null
            val title : String? = null
            val superChapterName : String? = null
            val niceDate : String? = null
            val fresh : Boolean? = false
            val collect : Boolean? = false
            val top : Boolean? = false
            val id : Int? = 0

        }
    }

}
//
//    {"data":{"curPage":2,"datas":
//        [{"apkLink":"",
//            "audit":1,
//            "author":"",
//            "canEdit":false,
//            "chapterId":502,
//            "chapterName":"自助",
//            "collect":false,
//            "courseId":13,
//            "desc":"",
//            "descMd":"",
//            "envelopePic":"",
//            "fresh":false,
//            "id":12707,
//            "link":"https://juejin.im/post/5e834bb5f265da480d61668d",
//            "niceDate":"2020-04-01 08:27",
//            "niceShareDate":"2020-04-01 08:27",
//            "origin":"",
//            "prefix":"",
//            "projectLink":"",
//            "publishTime":1585700820000,
//            "selfVisible":0,
//            "shareDate":1585700820000,
//            "shareUser":"Fly_with24",
//            "superChapterId":494,
//            "superChapterName":"广场Tab",
//            "tags":[],
//            "title":"【背上Jetpack之LiveData】ViewModel 的左膀右臂 数据驱动真的香",
//            "type":0,"userId":37531,"visible":1,"zan":0}
