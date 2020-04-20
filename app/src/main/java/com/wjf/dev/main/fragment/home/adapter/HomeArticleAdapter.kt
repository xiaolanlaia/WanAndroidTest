package com.wjf.dev.main.fragment.home.adapter

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.entity.HomeArticleBean
import com.wjf.dev.main.fragment.home.HomeViewModel
import com.wjf.dev.main.fragment.home.fragment.HomeArticleFragment
import com.wjf.dev.util.CodeUtil

/**
 * @author xiaolanlaia
 * @create 2020/4/11 9:56
 */


class HomeArticleAdapter:
    BaseQuickAdapter<HomeArticleBean.Data.Datas, BaseViewHolder>(R.layout.home_fragment_recycler_item) {

    lateinit var view : View
    private lateinit var onItemClickListener: OnItemClickListener





    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, name : String?, id : Int?, link : String?, title : String?)
        fun onItemClick(id : Int,collect : Boolean)
    }


    override fun convert(holder: BaseViewHolder, item: HomeArticleBean.Data.Datas) {



        holder
            .setText(R.id.article_title, item.title)
            .setText(R.id.article_author, item.shareUser)
            .setText(R.id.article_chapter, item.superChapterName)
            .setText(R.id.article_time, item.niceDate)



        when(item.collect){

            true -> holder.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext, R.mipmap.icon_collect_like))

            false -> holder.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext, R.mipmap.icon_collect_unlike))

        }

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

        holder.getView<TextView>(R.id.article_author).setOnClickListener {

            onItemClickListener.onItemClick(it,item.shareUser,item.userId,item.link,item.title)
        }
        holder.getView<TextView>(R.id.article_chapter).setOnClickListener {

            onItemClickListener.onItemClick(it,item.superChapterName,item.superChapterId,item.link,item.title)
        }

        holder.getView<RelativeLayout>(R.id.article_layout).setOnClickListener {

            onItemClickListener.onItemClick(it,item.superChapterName,item.superChapterId,item.link,item.title)
        }

        holder.getView<ImageView>(R.id.article_collect).setOnClickListener {

            if (!CodeUtil.checkIsLogin(it.context)) return@setOnClickListener

            onItemClickListener.onItemClick(item.id!!,item.collect!!)


            HomeViewModel.collectListener(object : HomeViewModel.Companion.SetCollectState{
                override fun onCollect(isCollect: Boolean) {
                    when(isCollect){

                        true ->{
                            holder.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext, R.mipmap.icon_collect_like))

                        }

                        false ->{
                            holder.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext, R.mipmap.icon_collect_unlike))

                        }

                    }
                }

            })

        }




    }
}

