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
import com.wjf.dev.databinding.HomeFragmentArticleBinding
import com.wjf.dev.main.fragment.home.HomeRepository
import com.wjf.dev.main.fragment.home.HomeVMFactory
import com.wjf.dev.main.fragment.home.HomeViewModel
import com.wjf.dev.main.fragment.home.adapter.HomeArticleAdapter
import com.wjf.dev.util.GlideImageLoader
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.home_fragment_article.*
import kotlinx.android.synthetic.main.home_fragment_recycler_item.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/17 9:28
 *
 */


class HomeArticleFragment : BaseMVVMFragment<HomeFragmentArticleBinding, HomeViewModel>(){

    val images = ArrayList<String>()
    val titles = ArrayList<String>()
    val urls = ArrayList<String>()

    override fun initViewModel(): HomeViewModel =
        ViewModelProvider(this,
            HomeVMFactory(HomeRepository())
        ).get(HomeViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.home_fragment_article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm


        initView()
        initRequest()

    }

    fun initView(){
        home_banner.setImageLoader(GlideImageLoader())
        home_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        home_banner.setDelayTime(2000)
        home_banner.setBannerAnimation(Transformer.DepthPage)
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

        home_banner.setOnBannerListener { position ->

            startActivity<TitleWithContentActivity>(
                Pair(Constants.SP.TITLE_ACTIVITY_TYPE,TitleWithContentActivity.TYPE_WEB_VIEW),
                Pair(Constants.SP.URL,urls[position]),
                Pair(Constants.SP.WEBVIEW_TITLE,titles[position])
            )

        }

        val homeAdapter = HomeArticleAdapter(R.layout.home_fragment_recycler_item)
        //设置layoutManager
        home_article_recycler.layoutManager = LinearLayoutManager(context)
        home_article_recycler.adapter = homeAdapter


        vm.articleList.observe(viewLifecycleOwner, Observer {

            homeAdapter.replaceData(it)



        })


        homeAdapter.setOnItemClickListener(object: HomeArticleAdapter.OnItemClickListener {
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


            override fun onItemClick(view: View, name: String?, id: Int?, link: String?, title: String?) {

                when(view.id){

                    article_chapter.id ->{
                        view.context.startActivity<TitleWithContentActivity>(
                            Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_ARTICLE_SORT_LIST),
                            Pair(Constants.SP.ARTICLE_TITLE,name),
                            Pair(Constants.SP.CID,id)
                        )
                    }
                    article_author.id ->{

                        view.context.startActivity<TitleWithContentActivity>(
                            Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_USER_ARTICLE_LIST),
                            Pair(Constants.SP.AUTHOR_NAME,name),
                            Pair(Constants.SP.AUTHOR_ID,id)
                        )
                    }

                    article_layout.id ->{
                        view.context.startActivity<TitleWithContentActivity>(
                            Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_WEB_VIEW),
                            Pair(Constants.SP.URL,link),
                            Pair(Constants.SP.WEBVIEW_TITLE,title)
                        )
                    }

                }

            }

        })
    }

    fun initRequest(){
        vm.getHomeBannerData(context!!)
        vm.getHomeArticleList()

    }

}