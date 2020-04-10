package com.wjf.dev.userArticle.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.entity.AuthorArticleBean
import com.wjf.dev.entity.HomeArticleBean
import org.jetbrains.anko.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/9 11:35
 *
 */


class AuthorArticleAdapter : BaseQuickAdapter<AuthorArticleBean.DataBean.ShareArticlesBean.DatasBean,BaseViewHolder>(R.layout.home_fragment_recycler_item) {

    lateinit var view : View

    /**
     * 更新数据
     */
    var list = mutableListOf<AuthorArticleBean.DataBean.ShareArticlesBean.DatasBean>()

    /**
     *更新数据
     */
    fun updateList(list: MutableList<AuthorArticleBean.DataBean.ShareArticlesBean.DatasBean>) {
        this.list = list
        notifyDataSetChanged()
    }
    override fun convert(holder: BaseViewHolder, item: AuthorArticleBean.DataBean.ShareArticlesBean.DatasBean) {
        holder.setText(R.id.article_title,item.title)
        holder.setText(R.id.article_author,item.shareUser)
        holder.setText(R.id.article_chapter,item.superChapterName)
        holder.setText(R.id.article_time,item.niceDate)


        when(false){

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

//        val article_title= view.findViewById<TextView>(R.id.article_title)
//        val article_author= view.findViewById<TextView>(R.id.article_author)
//        val article_chapter= view.findViewById<TextView>(R.id.article_chapter)
//
//
        view.setOnClickListener {
            it.context.startActivity<TitleWithContentActivity>(
                Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_WEB_VIEW),
                Pair(Constants.SP.URL,item.link),
                Pair(Constants.SP.WEBVIEW_TITLE,item.title)
            )
        }
//
//        article_author.setOnClickListener {
//            it.context.startActivity<TitleWithContentActivity>(
//                Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_USER_ARTICLE_LIST),
//                Pair(Constants.SP.AUTHOR_NAME,item.link),
//                Pair(Constants.SP.WEBVIEW_TITLE,item.title)
//            )
//        }
//        article_chapter.setOnClickListener {
//            it.context.startActivity<TitleWithContentActivity>(
//                Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_WEB_VIEW),
//                Pair(Constants.SP.URL,"https://wanandroid.com/user/2/articles/1"),
//                Pair(Constants.SP.WEBVIEW_TITLE,item.title)
//            )
//        }

//        holder.addOnClickListener(R.id.article_title)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
         view = LayoutInflater.from(parent.context).inflate(R.layout.home_fragment_recycler_item, parent, false)
        return TitleViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            convert(holder, list[holder.layoutPosition])


    }

    class TitleViewHolder(itemView: View) : BaseViewHolder(itemView)
}