package com.wjf.dev.main.fragment.offcialAccount.adapter

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.entity.HomeArticleBean
import com.wjf.dev.entity.OfficialAccountHistoryBean
import com.wjf.dev.main.fragment.home.adapter.HomeArticleAdapter

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/13 9:27
 *
 */


class OfficialAccountAdapter :
BaseQuickAdapter<OfficialAccountHistoryBean.dataBean.datasBean, BaseViewHolder>(R.layout.home_fragment_recycler_item) {

    lateinit var view : View
    private lateinit var onItemClickListener: OnItemClickListener


    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, link : String?, title : String?)
    }


    override fun convert(holder: BaseViewHolder, item: OfficialAccountHistoryBean.dataBean.datasBean) {
        holder
            .setText(R.id.article_title, item.title)
            .setText(R.id.article_author, item.author)
            .setText(R.id.article_chapter, item.superChapterName)
            .setText(R.id.article_time, item.niceDate)

        holder.setGone(R.id.article_top,false)
//        when(item.top){
//
//            true ->{
//                holder.setGone(R.id.article_top,true)
//            }
//
//            false ->{
//                holder.setGone(R.id.article_top,false)
//            }
//        }

        when(item.fresh){

            true ->{
                holder.setGone(R.id.article_fresh,true)
            }

            false ->{
                holder.setGone(R.id.article_fresh,false)
            }
        }

        holder.getView<RelativeLayout>(R.id.article_layout).setOnClickListener {

            onItemClickListener.onItemClick(it,item.link,item.title)
        }

    }
}

