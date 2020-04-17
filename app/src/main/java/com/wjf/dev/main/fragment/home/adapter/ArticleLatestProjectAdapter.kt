package com.wjf.dev.main.fragment.home.adapter

import android.view.View
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.common.MyApplication.Companion.context
import com.wjf.dev.entity.ProjectBean

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/17 12:26
 *
 */


class ArticleLatestProjectAdapter : BaseQuickAdapter<ProjectBean.dataBean.datasBean,BaseViewHolder>(R.layout.home_fragment_latest_project) {


    private lateinit var onItemClickListener : OnItemClickListener

    fun setOnItemClickListener(listener : OnItemClickListener){
        this.onItemClickListener = listener

    }
    interface OnItemClickListener{
        fun onItemClick(view : View,link : String?,title : String?)
    }
    override fun convert(helper: BaseViewHolder, item: ProjectBean.dataBean.datasBean) {

        helper
            .setText(R.id.project_title,item.title)
            .setText(R.id.project_content,item.desc)
            .setText(R.id.project_date,item.niceDate)
            .setText(R.id.project_tag,item.chapterName)
            .addOnClickListener(R.id.project_layout)
            .getView<CardView>(R.id.project_layout).setOnClickListener {
                onItemClickListener.onItemClick(it,item.link,item.title)
            }
        Glide.with(context)
            .load(item.envelopePic)
            .into(helper.getView(R.id.project_preview))


    }
}