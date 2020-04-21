package com.wjf.dev.userArticle.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.entity.AuthorArticleBean
import com.wjf.dev.entity.HomeArticleBean
import com.wjf.dev.entity.NickNameBean
import com.wjf.dev.main.fragment.home.HomeViewModel
import com.wjf.dev.util.CodeUtil
import org.jetbrains.anko.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/9 11:35
 *
 */


class AuthorArticleAdapter : BaseQuickAdapter<NickNameBean.dataBean.datasBean,BaseViewHolder>(R.layout.home_fragment_recycler_item) {


    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(id : Int,collect : Boolean)
    }

    override fun convert(helper: BaseViewHolder, item: NickNameBean.dataBean.datasBean) {
        helper
            .setText(R.id.article_title,item.title)
            .setText(R.id.article_author,returnAuthor(item))
            .setText(R.id.article_author,item.shareUser)
            .setText(R.id.article_chapter,item.superChapterName)
            .setText(R.id.article_time,item.niceDate)



        when(item.collect){

            true -> helper.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_collect_24dp))

            false -> helper.setImageDrawable(R.id.article_collect, ContextCompat.getDrawable(mContext,R.drawable.ic_favorite_gray_24dp))

        }
        when(false){

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


        helper.getView<RelativeLayout>(R.id.article_layout).setOnClickListener {

            it.context.startActivity<TitleWithContentActivity>(
                Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_WEB_VIEW),
                Pair(Constants.SP.URL,item.link),
                Pair(Constants.SP.WEBVIEW_TITLE,item.title)
            )
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
    fun returnAuthor(item: NickNameBean.dataBean.datasBean) : String{

        if (!TextUtils.isEmpty(item.author)) return item.author!!

        return item.shareUser!!
    }
}