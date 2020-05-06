package com.wjf.dev.main.fragment.home.adapter

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.common.MyApplication.Companion.context
import com.wjf.dev.entity.ArticleBean
import com.wjf.dev.main.fragment.home.HomeViewModel
import com.wjf.dev.util.CodeUtil
import com.wjf.dev.util.GlideUtils

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/17 12:26
 *
 */


class ArticleLatestProjectAdapter(layoutId : Int) : BaseQuickAdapter<ArticleBean.DataBean.DatasBean,BaseViewHolder>(layoutId) {


    private lateinit var onItemClickListener : OnItemClickListener

    fun setOnItemClickListener(listener : OnItemClickListener){
        this.onItemClickListener = listener

    }
    interface OnItemClickListener{
        fun onItemClick(view : View,link : String?,title : String?)
        fun onItemClick(id : Int,collect : Boolean)
    }
    override fun convert(helper: BaseViewHolder, item: ArticleBean.DataBean.DatasBean) {

        helper
            .setText(R.id.project_title,item.title)
            .setText(R.id.project_content,item.desc)
            .setText(R.id.tv_project_author_name,returnAuthor(item))
            .setText(R.id.project_date,item.niceDate)
            .setText(R.id.project_tag,item.chapterName)
            .addOnClickListener(R.id.project_layout)
            .getView<CardView>(R.id.project_layout).setOnClickListener {
                onItemClickListener.onItemClick(it,item.link,item.title)
            }

        GlideUtils.showBannerImage(mContext, helper.getView(R.id.project_preview), item.envelopePic)

        when(item.collect){

            true -> helper.setImageDrawable(R.id.project_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_collect_24dp))

            false -> helper.setImageDrawable(R.id.project_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_gray_24dp))

        }



        helper.getView<ImageView>(R.id.project_collect).setOnClickListener {

            if (!CodeUtil.checkIsLogin(it.context)) return@setOnClickListener

            onItemClickListener.onItemClick(item.id!!,item.collect!!)


            HomeViewModel.collectListener(object : HomeViewModel.Companion.SetCollectState{
                override fun onCollect(isCollect: Boolean) {
                    when(isCollect){

                        true ->{
                            item.collect = true
                            helper.setImageDrawable(R.id.project_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_collect_24dp))

                        }

                        false ->{
                            item.collect = false
                            helper.setImageDrawable(R.id.project_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_gray_24dp))

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