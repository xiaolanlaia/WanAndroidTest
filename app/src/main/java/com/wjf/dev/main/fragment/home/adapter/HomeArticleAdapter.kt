package com.wjf.dev.main.fragment.home.adapter

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.entity.HomeArticleBean

/**
 * @author xiaolanlaia
 * @create 2020/4/11 9:56
 */


class HomeArticleAdapter(layoutResId: Int) :
    BaseQuickAdapter<HomeArticleBean.Data.Datas, BaseViewHolder>(layoutResId) {

    lateinit var view : View
    private lateinit var onItemClickListener: OnItemClickListener


    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, link : String?, title : String?)
        fun onItemClick(view: View, name : String?, id : Int?)
    }


    override fun convert(holder: BaseViewHolder, item: HomeArticleBean.Data.Datas) {
        holder
            .setText(R.id.article_title, item.title)
            .setText(R.id.article_author, item.shareUser)
            .setText(R.id.article_chapter, item.superChapterName)
            .setText(R.id.article_time, item.niceDate)

        when(item.top){

            true ->{
                holder.setGone(R.id.article_top,true)
            }

            false ->{
                holder.setGone(R.id.article_top,false)
            }
        }

        when(item.fresh){

            true ->{
                holder.setGone(R.id.article_fresh,true)
            }

            false ->{
                holder.setGone(R.id.article_fresh,false)
            }
        }

        holder.getView<TextView>(R.id.article_title).setOnClickListener {

            onItemClickListener.onItemClick(it,item.link,item.title)
        }

        holder.getView<TextView>(R.id.article_author).setOnClickListener {

            onItemClickListener.onItemClick(it,item.shareUser,item.userId)
        }
        holder.getView<TextView>(R.id.article_chapter).setOnClickListener {

            onItemClickListener.onItemClick(it,item.superChapterName,item.superChapterId)
        }
    }
}

