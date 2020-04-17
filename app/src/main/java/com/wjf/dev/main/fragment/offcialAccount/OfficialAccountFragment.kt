package com.wjf.dev.main.fragment.offcialAccount

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.databinding.FragmentOfficialAccountBinding
import com.wjf.dev.databinding.HomeFragmentBinding
import com.wjf.dev.entity.OfficialAccountBean
import kotlinx.android.synthetic.main.fragment_official_account.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:11
 *
 */


class OfficialAccountFragment : BaseMVVMFragment<FragmentOfficialAccountBinding, OfficialAccountViewModel>() {
    override fun initViewModel(): OfficialAccountViewModel =
        ViewModelProvider(this, OfficialAccountVMFactory(OfficialAccountRepository())).get(OfficialAccountViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.fragment_official_account

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm

        initData()
        initRequest()

    }

    fun initRequest(){
        vm.getOfficialAccountList()
    }

    fun initData(){
        vm.articleList.observe(viewLifecycleOwner, Observer {
            initWeChatArticleFragment(it)

        })
    }

    fun initWeChatArticleFragment(dataList : List<OfficialAccountBean.dataBean>){

        val tabs = arrayListOf<String>()
        val fragments = arrayListOf<Fragment>()


        for (data in dataList){
            tabs.add(data.name!!)
            fragments.add(OfficialAccountArticleFragment.newInstance(data.id!!))
        }

        official_account_viewpager.adapter =officialAccountTabAdapter(childFragmentManager,tabs,fragments)
        official_account_tab.setViewPager(official_account_viewpager)


    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        when(hidden){
            false ->{
                initRequest()
            }
        }
    }


    class officialAccountTabAdapter(
        fragmentManager: FragmentManager,
        val tabs: List<String>,
        val fragments: List<Fragment>
    ) : FragmentStatePagerAdapter(fragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = tabs.size

        override fun getPageTitle(position: Int): CharSequence? = tabs[position]
    }
}