package com.wjf.dev.main.fragment.knowledge

import com.wjf.dev.entity.ArticleSortBean
import com.wjf.dev.entity.KnowledgeBean
import com.wjf.dev.util.RetrofitManager
import com.wjf.dev.util.doInBackground
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class KnowledgeRepository {

    /**
     * 获取体系列表数据
     */
    fun getSystemDataList() : Observable<KnowledgeBean>{

        return RetrofitManager.getSystemDataList().doInBackground()
    }

    /**
     * 某个分类下文章
     */
    fun getArticleSort(cid : Int) : Observable<ArticleSortBean>{

        return RetrofitManager.getArticleSort(cid).doInBackground()

    }
}