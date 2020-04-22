package com.wjf.dev.main.fragment.home.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.databinding.HomeFragmentBinding
import com.wjf.dev.main.fragment.home.HomeRepository
import com.wjf.dev.main.fragment.home.HomeVMFactory
import com.wjf.dev.main.fragment.home.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class HomeFragment : BaseMVVMFragment<HomeFragmentBinding, HomeViewModel>() {



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

    }

    fun initView(){



        home_viewpager.adapter =
            HomePageAdapter(childFragmentManager)
        home_tab.setupWithViewPager(home_viewpager)


    }



    fun initRequest(){



    }



    class HomePageAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){


        private val fragments = mutableListOf<Fragment>()
        private val titles = mutableListOf<String>()

        init {

            titles.add("首页")
            titles.add("广场")
            titles.add("项目")

            fragments.add(HomeArticleFragment())
            fragments.add(HomeSecondFragment())
            fragments.add(HomeLatestProjectFragment())
        }

        override fun getItem(position: Int): Fragment  = fragments[position]

        override fun getCount(): Int = fragments.size

        override fun getPageTitle(position: Int): CharSequence? = titles[position]


    }

}