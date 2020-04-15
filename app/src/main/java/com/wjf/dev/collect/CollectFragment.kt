package com.wjf.dev.collect

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.collect.adapter.CollectAdapter
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.databinding.CollectFragmentBinding
import com.wjf.dev.entity.CollectBean
import kotlinx.android.synthetic.main.collect_fragment.*
import kotlinx.android.synthetic.main.home_fragment_recycler_item.*
import org.jetbrains.anko.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 16:43
 *
 */


class CollectFragment : BaseMVVMFragment<CollectFragmentBinding,CollectViewModel>() {
    override fun initViewModel(): CollectViewModel =
        ViewModelProvider(this,CollectVMFactory(CollectRepository())).get(CollectViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.collect_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm

        initData()
        initRequest()


    }

    fun initData(){


        val collAdapter = CollectAdapter()
        //设置layoutManager
        collect_article_recycler.layoutManager = LinearLayoutManager(context)
        collect_article_recycler.adapter = collAdapter


        vm.collectList.observe(viewLifecycleOwner, Observer {
            collAdapter.addData(it as MutableList<CollectBean.dataBean.datasBean>)

        })

        collAdapter.setOnItemClickListener(object: CollectAdapter.OnItemClickListener {


            override fun onItemClick(view: View, link: String?, title: String?) {

                view.context.startActivity<TitleWithContentActivity>(
                    Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_WEB_VIEW),
                    Pair(Constants.SP.URL,link),
                    Pair(Constants.SP.WEBVIEW_TITLE,title)
                )
            }
        })

    }

    fun initRequest(){

        vm.getCollectList()
    }
}