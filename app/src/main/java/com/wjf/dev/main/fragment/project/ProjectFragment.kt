package com.wjf.dev.main.fragment.project

import androidx.lifecycle.ViewModelProvider
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.databinding.HomeFragmentBinding

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class ProjectFragment : BaseMVVMFragment<HomeFragmentBinding, ProjectViewModel>() {
    override fun initViewModel(): ProjectViewModel =
        ViewModelProvider(this, ProjectVMFactory(ProjectRepository())).get(ProjectViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.home_fragment
}