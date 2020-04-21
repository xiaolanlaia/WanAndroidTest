package com.wjf.dev.main.fragment.offcialAccount.adapter

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.entity.HomeArticleBean
import com.wjf.dev.main.fragment.home.HomeViewModel
import com.wjf.dev.util.CodeUtil

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/13 9:27
 *
 */


class OfficialAccountAdapter :
BaseQuickAdapter<HomeArticleBean.DataBean.DatasBean, BaseViewHolder>(R.layout.home_fragment_recycler_item) {

    lateinit var view : View
    private lateinit var onItemClickListener: OnItemClickListener


    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, link : String?, title : String?)
        fun onItemClick(id : Int,collect : Boolean)
    }


    override fun convert(holder: BaseViewHolder, item: HomeArticleBean.DataBean.DatasBean) {
        holder
            .setText(R.id.article_title, item.title)
            .setText(R.id.article_author, returnAuthor(item))
            .setText(R.id.article_chapter, item.superChapterName)
            .setText(R.id.article_time, item.niceDate)

        holder.setGone(R.id.article_top,false)

        when(item.collect){

            true -> holder.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_collect_24dp))

            false -> holder.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext,R.drawable.ic_favorite_gray_24dp))

        }
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

        holder.getView<ImageView>(R.id.article_collect).setOnClickListener {

            if (!CodeUtil.checkIsLogin(it.context)) return@setOnClickListener

            onItemClickListener.onItemClick(item.id!!,item.collect!!)


            HomeViewModel.collectListener(object : HomeViewModel.Companion.SetCollectState{
                override fun onCollect(isCollect: Boolean) {
                    when(isCollect){

                        true ->{
                            item.collect = true
                            holder.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_collect_24dp))

                        }

                        false ->{
                            item.collect = false
                            holder.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_gray_24dp))

                        }

                    }
                }

            })

        }

    }
    fun returnAuthor(item: HomeArticleBean.DataBean.DatasBean) : String{

        if (!TextUtils.isEmpty(item.author)) return item.author!!

        return item.shareUser!!
    }
}

