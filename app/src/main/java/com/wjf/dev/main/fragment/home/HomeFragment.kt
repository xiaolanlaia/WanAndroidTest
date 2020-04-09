package com.wjf.dev.main.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.just.agentweb.AgentWeb
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.databinding.HomeFragmentBinding
import com.wjf.dev.entity.HomeArticleBean
import com.wjf.dev.main.fragment.home.adapter.ArticleAdapter
import com.wjf.dev.util.GlideImageLoader
import com.wjf.dev.util.toast
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.home_fragment.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class HomeFragment : BaseMVVMFragment<HomeFragmentBinding, HomeViewModel>() {

    val images = ArrayList<String>()
    val titles = ArrayList<String>()
    val urls = ArrayList<String>()

    override fun initViewModel(): HomeViewModel =
        ViewModelProvider(this, HomeVMFactory(
            HomeRepository()
        )
        ).get(HomeViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.home_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm

        initView()
        initRequest()
        setData()



    }

    fun initView(){

        home_banner.setImageLoader(GlideImageLoader())
        home_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        home_banner.setDelayTime(2000)
        home_banner.setBannerAnimation(Transformer.DepthPage)

        home_banner.setOnBannerListener { position ->

            startActivity<TitleWithContentActivity>(
                Pair(Constants.SP.TITLE_ACTIVITY_TYPE,TitleWithContentActivity.TYPE_WEB_VIEW),
                Pair(Constants.SP.URL,urls[position]),
                Pair(Constants.SP.WEBVIEW_TITLE,titles[position])
            )

        }


        //设置layoutManager
        home_article_recycler.layoutManager = LinearLayoutManager(context)

        val homeAdapter = ArticleAdapter()
//        homeAdapter.setOnItemChildClickListener(this)
        home_article_recycler.adapter = homeAdapter




        vm.articleList.observe(viewLifecycleOwner, Observer {

            homeAdapter.updateList(it as MutableList<HomeArticleBean.Data.Datas>)

        })

        vm.articleList.observe(viewLifecycleOwner, Observer {

            homeAdapter.updateList(it as MutableList<HomeArticleBean.Data.Datas>)

        })


    }



    fun initRequest(){

        vm.getHomeBannerData(context!!)
        vm.getHomeArticleList()
    }


    fun setData(){
        vm.bannerData.observe(viewLifecycleOwner, Observer {

            images.clear()
            titles.clear()
            urls.clear()

            it.forEach {
                images.add(it.imagePath!!)
                titles.add(it.title!!)
                urls.add(it.url!!)
            }
            home_banner.setImages(images)
            home_banner.setBannerTitles(titles)
            home_banner.start()


        })
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        when(hidden){

            false ->{
                initRequest()
            }

        }
    }
}