package com.wjf.dev.main.fragment.home.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.databinding.HomeFragmentBinding
import com.wjf.dev.main.fragment.home.HomeRepository
import com.wjf.dev.main.fragment.home.HomeVMFactory
import com.wjf.dev.main.fragment.home.HomeViewModel
import com.wjf.dev.util.GlideImageLoader
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.home_fragment.*
import org.jetbrains.anko.support.v4.startActivity

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

        home_viewpager.adapter =
            HomePageAdapter(childFragmentManager)
        home_tab.setupWithViewPager(home_viewpager)


    }



    fun initRequest(){

        vm.getHomeBannerData(context!!)

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

    class HomePageAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){


        private val fragments = mutableListOf<Fragment>()
        private val titles = mutableListOf<String>()

        init {

            titles.add("首页")
            titles.add("广场")
            titles.add("项目")

            fragments.add(HomeArticleFragment.newInstance(HomeArticleFragment.TYPE_ZERO))
            fragments.add(HomeArticleFragment.newInstance(HomeArticleFragment.TYPE_ONE))
            fragments.add(HomeLatestProjectFragment())
        }

        override fun getItem(position: Int): Fragment  = fragments[position]

        override fun getCount(): Int = fragments.size

        override fun getPageTitle(position: Int): CharSequence? = titles[position]


    }

}