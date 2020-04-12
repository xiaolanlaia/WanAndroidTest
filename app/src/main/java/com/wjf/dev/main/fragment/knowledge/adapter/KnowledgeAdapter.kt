package com.wjf.dev.main.fragment.knowledge.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wjf.dev.R
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.entity.KnowledgeBean
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import org.jetbrains.anko.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/11 9:36
 *
 */


class KnowledgeAdapter : BaseQuickAdapter<KnowledgeBean.DataBean,BaseViewHolder>(R.layout.knowledge_fragment_item){

    lateinit var view : View

    override fun convert(helper: BaseViewHolder, item: KnowledgeBean.DataBean) {


        helper.setText(R.id.item_title, item.name)
        val tagLayput = helper.getView<TagFlowLayout>(R.id.item_tag)
        tagLayput.adapter =
            object : TagAdapter<KnowledgeBean.DataBean.childrenBean>(item.children){
                override fun getView(
                    parent: FlowLayout?,
                    position: Int,
                    t: KnowledgeBean.DataBean.childrenBean?
                ): View {
                    val tagView : TextView =
                        LayoutInflater.from(mContext).inflate(R.layout.knowledge_fragment_item_item, parent,false) as TextView
                    tagView.text = item.children!![position].name
//                    tagView.setTextColor(ColorUtil.randomColor())
                    return tagView

                }

            }

        tagLayput.setOnTagClickListener { view, position, parent ->
            Log.d("__id","${item.children!![position].id}")
            view.context.startActivity<TitleWithContentActivity>(
                Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_ARTICLE_SORT_LIST),
                Pair(Constants.SP.ARTICLE_TITLE,item.children[position].name),
                Pair(Constants.SP.CID,item.children[position].id)
            )
            false
        }
//        tagLayput.setOnSelectListener(TagFlowLayout.OnSelectListener {
//                selectPosSet -> LogUtil.e("选中了tag:$selectPosSet")
//        })
//        adapter.setSelectedList(0)//默认选中第一个





    }

}