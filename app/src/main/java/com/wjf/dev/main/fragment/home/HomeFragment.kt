package com.wjf.dev.main.fragment.home

import androidx.lifecycle.ViewModelProvider
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.databinding.FragmentHomeBinding
import com.wjf.dev.main.fragment.project.ProjectRepository
import com.wjf.dev.main.fragment.project.ProjectVMFactory
import com.wjf.dev.main.fragment.project.ProjectViewModel

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class HomeFragment : BaseMVVMFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun initViewModel(): HomeViewModel =
        ViewModelProvider(this, HomeVMFactory(
            HomeRepository()
        )
        ).get(HomeViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.fragment_home
}