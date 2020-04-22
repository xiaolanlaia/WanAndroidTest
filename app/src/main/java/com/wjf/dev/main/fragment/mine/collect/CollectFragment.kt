package com.wjf.dev.main.fragment.mine.collect

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.databinding.MineFragmentCollectBinding
import com.wjf.dev.main.fragment.home.adapter.HomeArticleAdapter
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


        val homeArticleAdapter = HomeArticleAdapter()
        //设置layoutManager
        collect_article_recycler.layoutManager = LinearLayoutManager(context)
        collect_article_recycler.adapter = homeArticleAdapter


        vm.collectList.observe(viewLifecycleOwner, Observer {

            when(it.size){

                0 ->{
                    collect_null.visibility = View.VISIBLE
                }

                else ->{
                    homeArticleAdapter.replaceData(it)

                }
            }

        })

        homeArticleAdapter.setOnItemClickListener(object: HomeArticleAdapter.OnItemClickListener {


            override fun onItemClick(id: Int, collect: Boolean) {

                when (collect) {

                    true -> {

                        vm.mineUnCollect(id)
                    }

                    false -> {
                    }
                }

            }



            override fun onItemClick(view: View, name: String?, id: Int?, link: String?, title: String?) {

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