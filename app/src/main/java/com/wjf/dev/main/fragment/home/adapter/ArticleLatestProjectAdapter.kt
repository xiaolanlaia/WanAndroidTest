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
import com.wjf.dev.entity.HomeArticleBean
import com.wjf.dev.main.fragment.home.HomeViewModel
import com.wjf.dev.util.CodeUtil

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/17 12:26
 *
 */


class ArticleLatestProjectAdapter : BaseQuickAdapter<HomeArticleBean.DataBean.DatasBean,BaseViewHolder>(R.layout.home_fragment_latest_project) {


    private lateinit var onItemClickListener : OnItemClickListener

    fun setOnItemClickListener(listener : OnItemClickListener){
        this.onItemClickListener = listener

    }
    interface OnItemClickListener{
        fun onItemClick(view : View,link : String?,title : String?)
        fun onItemClick(id : Int,collect : Boolean)
    }
    override fun convert(helper: BaseViewHolder, item: HomeArticleBean.DataBean.DatasBean) {

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

        Glide.with(context)
            .load(item.envelopePic)
            .into(helper.getView(R.id.project_preview))

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

    fun returnAuthor(item: HomeArticleBean.DataBean.DatasBean) : String{

        if (!TextUtils.isEmpty(item.author)) return item.author!!

        return item.shareUser!!
    }
}