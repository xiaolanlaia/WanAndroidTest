package com.wjf.dev.main.fragment.mine.collect.adapter

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.entity.CollectBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 17:03
 *
 */


class CollectAdapter :
    BaseQuickAdapter<CollectBean.dataBean.datasBean, BaseViewHolder>(R.layout.home_fragment_recycler_item) {

    lateinit var view : View
    private lateinit var onItemClickListener: OnItemClickListener


    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, link : String?, title : String?)
    }


    override fun convert(holder: BaseViewHolder, item: CollectBean.dataBean.datasBean) {
        holder
            .setText(R.id.article_title, item.title)
            .setText(R.id.article_author, item.author)
            .setText(R.id.article_chapter, item.chapterName)
            .setText(R.id.article_time, item.niceDate)

        holder.setGone(R.id.article_top,false)
        holder.setGone(R.id.article_fresh,false)



        holder.getView<RelativeLayout>(R.id.article_layout).setOnClickListener {

            onItemClickListener.onItemClick(it,item.link,item.title)
        }

    }
}

