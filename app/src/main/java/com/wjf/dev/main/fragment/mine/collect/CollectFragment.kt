package com.wjf.dev.main.fragment.mine.collect

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.main.fragment.mine.collect.adapter.CollectAdapter
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.databinding.MineFragmentCollectBinding
import com.wjf.dev.main.fragment.mine.MineRepository
import com.wjf.dev.main.fragment.mine.MineVMFactory
import com.wjf.dev.main.fragment.mine.MineViewModel
import kotlinx.android.synthetic.main.mine_fragment_collect.*
import org.jetbrains.anko.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 16:43
 *
 */


class CollectFragment : BaseMVVMFragment<MineFragmentCollectBinding, MineViewModel>() {
    override fun initViewModel(): MineViewModel =
        ViewModelProvider(this,
            MineVMFactory(MineRepository())
        ).get(MineViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.mine_fragment_collect

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

            when(it.size){

                0 ->{
                    collect_null.visibility = View.VISIBLE
                }

                else ->{
                    collAdapter.replaceData(it)

                }
            }

        })

        collAdapter.setOnItemClickListener(object: CollectAdapter.OnItemClickListener {

            override fun onItemClick(id: Int, collect : Boolean,title : String, author : String, link : String) {

                when(collect){

                    true ->{
                        
                        vm.mineUnCollect(id)
                    }

                    false ->{
                        vm.addCollect(title, author, link)
                    }
                }

            }


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