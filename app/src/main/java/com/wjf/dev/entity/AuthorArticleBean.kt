package com.wjf.dev.entity

import com.sun.dev.entity.BaseBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/10 10:11
 *
 */


class AuthorArticleBean : BaseBean() {

    var data : DataBean? = null


    class DataBean{

        var coinInfo : coinInfoBean? = null
        var shareArticles : ShareArticlesBean? = null

        class coinInfoBean{
            var coinCount : Int? = 0
            var level : Int? = 0
            var rank : Int? = 0
            var userId : Int? = 0
            var username : String? = null
        }

        class ShareArticlesBean{
            var curPage : Int? = 0
            var datas : ArrayList<DatasBean>? = null

            class DatasBean{
                var author : String? = null
                var chapterName : String? = null
                var link : String? = null
                var niceDate : String? = null
                var superChapterName : String? = null
                var title : String? = null
                var shareUser : String? = null
                var origin : String? = null
                var id : Int? = 0
                var chapterId : Int? = 0
                var superChapterId : Int? = 0
                var userId : Int? = 0
                var collect : Boolean? = false
                var fresh : Boolean? = false
                var tags : ArrayList<TagsBean>? = null

                class TagsBean{
                    var name : String? = null
                    var url : String? = null
                }
            }
        }


    }


}