package com.wjf.dev.main.fragment.knowledge

import androidx.lifecycle.ViewModelProvider
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.databinding.FragmentHomeBinding

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class KnowledgeFragment : BaseMVVMFragment<FragmentHomeBinding, KnowledgeViewModel>() {
    override fun initViewModel(): KnowledgeViewModel =
        ViewModelProvider(this, KnowledgeVMFactory(KnowledgeRepository())).get(KnowledgeViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.fragment_home
}