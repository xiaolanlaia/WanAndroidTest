package com.wjf.dev.main.fragment.offcialAccount

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


class OfficialAccountFragment : BaseMVVMFragment<HomeFragmentBinding, OfficialAccountViewModel>() {
    override fun initViewModel(): OfficialAccountViewModel =
        ViewModelProvider(this, OfficialAccountVMFactory(OfficialAccountRepository())).get(OfficialAccountViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.home_fragment
}