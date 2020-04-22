package com.wjf.dev.main.fragment.offcialAccount

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.databinding.FragmentOfficialAccountArticleBinding
import com.wjf.dev.main.fragment.home.adapter.HomeArticleAdapter
import kotlinx.android.synthetic.main.fragment_official_account_article.*
import org.jetbrains.anko.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/13 8:22
 *
 */


class OfficialAccountArticleFragment : BaseMVVMFragment<FragmentOfficialAccountArticleBinding,OfficialAccountViewModel>() {
    lateinit var homeArticleAdapter: HomeArticleAdapter

    companion object {
        fun newInstance(id : Int) : OfficialAccountArticleFragment {
            val bundle = Bundle()
            bundle.putInt("id", id)
            val fragment = OfficialAccountArticleFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun initViewModel(): OfficialAccountViewModel =
        ViewModelProvider(this,OfficialAccountVMFactory(OfficialAccountRepository())).get(OfficialAccountViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.fragment_official_account_article
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm
        initData()
        initRequest()
    }

    fun initRequest(){
        vm.getHistoryData(arguments!!.getInt("id"))
    }

    fun initData(){
        homeArticleAdapter = HomeArticleAdapter()
        //设置layoutManager
        official_account_article_recycler.layoutManager = LinearLayoutManager(context)
        official_account_article_recycler.adapter = homeArticleAdapter
        vm.historyList.observe(viewLifecycleOwner, Observer {
            homeArticleAdapter.replaceData(it)
        })

        homeArticleAdapter.setOnItemClickListener(object: HomeArticleAdapter.OnItemClickListener {
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

                    view.context.startActivity<TitleWithContentActivity>(
                        Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_WEB_VIEW),
                        Pair(Constants.SP.URL,link),
                        Pair(Constants.SP.WEBVIEW_TITLE,title)
                    )
                }
            })
    }
}