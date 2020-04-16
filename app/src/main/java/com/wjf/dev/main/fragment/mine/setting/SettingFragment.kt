package com.wjf.dev.main.fragment.mine.setting

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.databinding.MineFragmentSettingBinding
import com.wjf.dev.main.fragment.mine.MineRepository
import com.wjf.dev.main.fragment.mine.MineVMFactory
import com.wjf.dev.main.fragment.mine.MineViewModel

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/16 8:32
 *
 */


class SettingFragment : BaseMVVMFragment<MineFragmentSettingBinding,MineViewModel>(){
    override fun initViewModel(): MineViewModel =
        ViewModelProvider(this,MineVMFactory(MineRepository())).get(MineViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.mine_fragment_setting

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm


    }

    fun initRequest(){


    }
}