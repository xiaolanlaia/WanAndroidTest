package com.wjf.dev.main.fragment.home.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.databinding.HomeFragmentArticleSecondBinding
import com.wjf.dev.main.fragment.home.HomeRepository
import com.wjf.dev.main.fragment.home.HomeVMFactory
import com.wjf.dev.main.fragment.home.HomeViewModel
import com.wjf.dev.main.fragment.home.adapter.ArticleLatestProjectAdapter
import kotlinx.android.synthetic.main.home_fragment_article_second.*
import org.jetbrains.anko.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/17 12:45
 *
 */


class HomeLatestProjectFragment : BaseMVVMFragment<HomeFragmentArticleSecondBinding, HomeViewModel>() {

    val articleLatestProjectAdapter = ArticleLatestProjectAdapter()
    override fun initViewModel(): HomeViewModel =
        ViewModelProvider(this,
            HomeVMFactory(HomeRepository())
        ).get(HomeViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.home_fragment_article_second

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm
        initView()
        initData()
        initRequest()
    }

    fun initView(){

        home_article_recycler.layoutManager = LinearLayoutManager(context)
        home_article_recycler.adapter = articleLatestProjectAdapter

        articleLatestProjectAdapter.setOnItemClickListener(object : ArticleLatestProjectAdapter.OnItemClickListener{
            override fun onItemClick(id: Int, collect : Boolean) {

                when(collect){

                    true ->{

                        vm.unCollect(id)
                    }

                    false ->{
                        vm.collect(id)
                    }
                }

            }

            override fun onItemClick(view: View, link: String?, title: String?) {
                view.context.startActivity<TitleWithContentActivity>(
                    Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_WEB_VIEW),
                    Pair(Constants.SP.URL,link),
                    Pair(Constants.SP.WEBVIEW_TITLE,title)
                )            }

        })

    }

    fun initData(){

        vm.projectArticleList.observe(viewLifecycleOwner, Observer {
            articleLatestProjectAdapter.replaceData(it)

        })
    }

    fun initRequest(){
        vm.getLatestProject()
    }

}