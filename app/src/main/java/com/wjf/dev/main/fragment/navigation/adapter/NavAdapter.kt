package com.wjf.dev.main.fragment.navigation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.entity.NavArticleBean
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import org.jetbrains.anko.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/14 7:35
 *
 */


class NavAdapter : BaseQuickAdapter<NavArticleBean.dataBean,BaseViewHolder>(R.layout.knowledge_fragment_item) {

    override fun convert(helper: BaseViewHolder, item: NavArticleBean.dataBean) {

        helper.setText(R.id.item_title, item.name)
        val tagLayout = helper.getView<TagFlowLayout>(R.id.item_tag)
        tagLayout.adapter =
            object : TagAdapter<NavArticleBean.dataBean.articlesBean>(item.articles){
                override fun getView(
                    parent: FlowLayout?,
                    position: Int,
                    t: NavArticleBean.dataBean.articlesBean?
                ): View {
                    val tagView : TextView =
                        LayoutInflater.from(mContext).inflate(R.layout.knowledge_fragment_item_item, parent,false) as TextView
                    tagView.text = item.articles!![position].title
//                    tagView.setTextColor(ColorUtil.randomColor())
                    return tagView

                }

            }

        tagLayout.setOnTagClickListener { view, position, parent ->

            view.context.startActivity<TitleWithContentActivity>(
                Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_WEB_VIEW),
                Pair(Constants.SP.URL, item.articles!![position].link),
                Pair(Constants.SP.WEBVIEW_TITLE,item.articles!![position].title)
            )
            false
        }
    }
}