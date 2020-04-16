package com.wjf.dev.main.fragment.mine.rank

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.databinding.MineFragmentIntegralBinding
import com.wjf.dev.main.fragment.mine.MineRepository
import com.wjf.dev.main.fragment.mine.MineVMFactory
import com.wjf.dev.main.fragment.mine.MineViewModel
import com.wjf.dev.main.fragment.mine.rank.adapter.RankAdapter
import kotlinx.android.synthetic.main.mine_fragment_integral.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/16 8:32
 *
 */


class RankFragment : BaseMVVMFragment<MineFragmentIntegralBinding,MineViewModel>(){

    val rankAdapter = RankAdapter()
    override fun initViewModel(): MineViewModel =
        ViewModelProvider(this,MineVMFactory(MineRepository())).get(MineViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.mine_fragment_integral

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm
        initRequest()
        initView()
    }

    fun initView(){

        integral_recycler.layoutManager = LinearLayoutManager(context)
        integral_recycler.adapter = rankAdapter

        vm.integralRankList.observe(viewLifecycleOwner, Observer {


            rankAdapter.replaceData(it)
        })
    }

    fun initRequest(){

        vm.getIntegralRank()
    }
}