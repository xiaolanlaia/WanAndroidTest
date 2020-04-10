package com.wjf.dev.main.fragment.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.entity.HomeArticleBean
import org.jetbrains.anko.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/9 11:35
 *
 */


class ArticleAdapter : BaseQuickAdapter<HomeArticleBean.Data.Datas,BaseViewHolder>(R.layout.home_fragment_recycler_item) {

    lateinit var view : View
    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(view: View,link : String?,title : String?)
        fun onItemClick(view: View,name : String?,id : Int?)
    }

    /**
     * 更新数据
     */
    var list = mutableListOf<HomeArticleBean.Data.Datas>()

    /**
     *更新数据
     */
    fun updateList(list: MutableList<HomeArticleBean.Data.Datas>) {
        this.list = list
        notifyDataSetChanged()
    }
    override fun convert(holder: BaseViewHolder, item: HomeArticleBean.Data.Datas) {
        holder.setText(R.id.article_title,item.title)
        holder.setText(R.id.article_author,item.shareUser)
        holder.setText(R.id.article_chapter,item.superChapterName)
        holder.setText(R.id.article_time,item.niceDate)





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

        holder.getView<TextView>(R.id.article_title).setOnClickListener {

            onItemClickListener.onItemClick(it,item.link,item.title)
        }

        holder.getView<TextView>(R.id.article_author).setOnClickListener {

            onItemClickListener.onItemClick(it,item.shareUser,item.userId)
        }
        holder.getView<TextView>(R.id.article_chapter).setOnClickListener {

            onItemClickListener.onItemClick(it,item.superChapterName,item.superChapterId)
        }


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