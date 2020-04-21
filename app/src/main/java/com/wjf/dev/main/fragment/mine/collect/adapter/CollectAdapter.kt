package com.wjf.dev.main.fragment.mine.collect.adapter

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.entity.CollectBean
import com.wjf.dev.main.fragment.home.HomeViewModel
import com.wjf.dev.util.CodeUtil

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
        fun onItemClick(id : Int, collect : Boolean,title : String, author : String, link : String)
    }


    override fun convert(helper: BaseViewHolder, item: CollectBean.dataBean.datasBean) {
        helper
            .setText(R.id.article_title, item.title)
            .setText(R.id.article_author, returnAuthor(item))
            .setText(R.id.article_chapter, item.chapterName)
            .setText(R.id.article_time, item.niceDate)

        when(item.collect){

            true -> helper.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_collect_24dp))

            false -> helper.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext,R.drawable.ic_favorite_gray_24dp))

        }
        helper.setGone(R.id.article_top,false)
        helper.setGone(R.id.article_fresh,false)


        helper.getView<RelativeLayout>(R.id.article_layout).setOnClickListener {

            onItemClickListener.onItemClick(it,item.link,item.title)
        }

        helper.getView<ImageView>(R.id.article_collect).setOnClickListener {

            if (!CodeUtil.checkIsLogin(it.context)) return@setOnClickListener

            onItemClickListener.onItemClick(item.id!!,item.collect!!,item.title!!,returnAuthor(item),item.link!!)

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
    fun returnAuthor(item: CollectBean.dataBean.datasBean) : String{

        if (!TextUtils.isEmpty(item.author)) return item.author!!

        return item.shareUser!!
    }
}

