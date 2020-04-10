package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/10 10:11
 *
 */


class AuthorArticleBean : BaseBean() {

    val data : DataBean? = null


    class DataBean{

        val coinInfo : coinInfoBean? = null
        val shareArticles : ShareArticlesBean? = null

        class coinInfoBean{
            val coinCount : Int? = 0
            val level : Int? = 0
            val rank : Int? = 0
            val userId : Int? = 0
            val username : String? = null
        }

        class ShareArticlesBean{
            val curPage : Int? = 0
            val datas : ArrayList<DatasBean>? = null

            class DatasBean{
                val author : String? = null
                val chapterName : String? = null
                val link : String? = null
                val niceDate : String? = null
                val superChapterName : String? = null
                val title : String? = null
                val shareUser : String? = null
                val id : Int? = 0
                val chapterId : Int? = 0
                val superChapterId : Int? = 0
                val userId : Int? = 0
                val collect : Boolean? = false
                val fresh : Boolean? = false
                val tags : ArrayList<TagsBean>? = null

                class TagsBean{
                    val name : String? = null
                    val url : String? = null
                }
            }
        }


    }




}