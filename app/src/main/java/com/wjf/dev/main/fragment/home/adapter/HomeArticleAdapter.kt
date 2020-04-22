package com.wjf.dev.main.fragment.home.adapter

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.entity.ArticleBean
import com.wjf.dev.main.fragment.home.HomeViewModel
import com.wjf.dev.util.CodeUtil

/**
 * @author xiaolanlaia
 * @create 2020/4/11 9:56
 */


class HomeArticleAdapter(layoutId : Int):
    BaseQuickAdapter<ArticleBean.DataBean.DatasBean, BaseViewHolder>(layoutId) {

    private lateinit var onItemClickListener: OnItemClickListener


    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, name : String?, id : Int?, link : String?, title : String?)
        fun onItemClick(id : Int,collect : Boolean)
    }


    override fun convert(helper: BaseViewHolder, item: ArticleBean.DataBean.DatasBean) {



        helper
            .setText(R.id.article_title, item.title)
            .setText(R.id.article_author, returnAuthor(item))
            .setText(R.id.article_chapter, item.superChapterName)
            .setText(R.id.article_time, item.niceDate)



        when(item.collect){

            true -> helper.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_collect_24dp))

            false -> helper.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext,R.drawable.ic_favorite_gray_24dp))

        }

        when(item.top){

            true ->{
                helper.setGone(R.id.article_top,true)
            }

            false ->{
                helper.setGone(R.id.article_top,false)
            }
        }

        when(item.fresh){

            true ->{
                helper.setGone(R.id.article_fresh,true)
            }

            false ->{
                helper.setGone(R.id.article_fresh,false)
            }
        }

        helper.getView<TextView>(R.id.article_author).setOnClickListener {

            onItemClickListener.onItemClick(it,returnAuthor(item),item.id,item.link,item.title)
        }
        helper.getView<TextView>(R.id.article_chapter).setOnClickListener {

            onItemClickListener.onItemClick(it,item.superChapterName,item.superChapterId,item.link,item.title)
        }

        helper.getView<RelativeLayout>(R.id.article_layout).setOnClickListener {

            onItemClickListener.onItemClick(it,item.superChapterName,item.superChapterId,item.link,item.title)
        }

        helper.getView<ImageView>(R.id.article_collect).setOnClickListener {

            if (!CodeUtil.checkIsLogin(it.context)) return@setOnClickListener

            onItemClickListener.onItemClick(item.id!!,item.collect!!)


            HomeViewModel.collectListener(object : HomeViewModel.Companion.SetCollectState{
                override fun onCollect(isCollect: Boolean) {
                    when(isCollect){

                        true ->{
                            item.collect = true
                            helper.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_collect_24dp))

                        }

                        false ->{
                            item.collect = false
                            helper.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_gray_24dp))

                        }

                    }
                }

            })

        }




    }

    fun returnAuthor(item: ArticleBean.DataBean.DatasBean) : String{

        if (!TextUtils.isEmpty(item.author)) return item.author!!

        return item.shareUser!!
    }
}

