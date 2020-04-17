package com.wjf.dev.main.fragment.home.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.databinding.HomeFragmentArticleBinding
import com.wjf.dev.entity.HomeArticleBean
import com.wjf.dev.main.fragment.home.HomeRepository
import com.wjf.dev.main.fragment.home.HomeVMFactory
import com.wjf.dev.main.fragment.home.HomeViewModel
import com.wjf.dev.main.fragment.home.adapter.HomeArticleAdapter
import kotlinx.android.synthetic.main.home_fragment_article.*
import kotlinx.android.synthetic.main.home_fragment_recycler_item.*
import org.jetbrains.anko.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/17 9:28
 *
 */


class HomeArticleFragment : BaseMVVMFragment<HomeFragmentArticleBinding, HomeViewModel>(){

    lateinit var homeAdapter : HomeArticleAdapter

    companion object{
        const val TYPE = "type"
        const val TYPE_ZERO = 0
        const val TYPE_ONE = 1
        const val TYPE_TWO = 2


        fun newInstance(type : Int) : HomeArticleFragment {

            val fragment = HomeArticleFragment()
            val bundle = Bundle()
            bundle.putInt(TYPE,type)
            fragment.arguments = bundle
            return fragment


        }
    }
    override fun initViewModel(): HomeViewModel =
        ViewModelProvider(this,
            HomeVMFactory(HomeRepository())
        ).get(HomeViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.home_fragment_article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm


        initView()
        initRequest()

    }

    fun initView(){

        homeAdapter = HomeArticleAdapter()
        //设置layoutManager
        home_article_recycler.layoutManager = LinearLayoutManager(context)
        home_article_recycler.adapter = homeAdapter


        vm.articleList.observe(viewLifecycleOwner, Observer {
            homeAdapter.addData(it as MutableList<HomeArticleBean.Data.Datas>)

        })


        homeAdapter.setOnItemClickListener(object: HomeArticleAdapter.OnItemClickListener {


            override fun onItemClick(view: View, name: String?, id: Int?, link: String?, title: String?) {

                when(view.id){

                    article_chapter.id ->{
                        view.context.startActivity<TitleWithContentActivity>(
                            Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_ARTICLE_SORT_LIST),
                            Pair(Constants.SP.ARTICLE_TITLE,name),
                            Pair(Constants.SP.CID,id)
                        )
                    }
                    article_author.id ->{

                        view.context.startActivity<TitleWithContentActivity>(
                            Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_USER_ARTICLE_LIST),
                            Pair(Constants.SP.AUTHOR_NAME,name),
                            Pair(Constants.SP.AUTHOR_ID,id)
                        )
                    }

                    article_layout.id ->{
                        view.context.startActivity<TitleWithContentActivity>(
                            Pair(Constants.SP.TITLE_ACTIVITY_TYPE, TitleWithContentActivity.TYPE_WEB_VIEW),
                            Pair(Constants.SP.URL,link),
                            Pair(Constants.SP.WEBVIEW_TITLE,title)
                        )
                    }

                }

            }

        })
    }

    fun initRequest(){

        when(arguments!!.getInt(TYPE)){

            TYPE_ZERO ->{
                vm.getHomeArticleList()
            }

            TYPE_ONE ->{

                vm.getSquareList()
            }

            TYPE_TWO ->{

                vm.getLatestProject()
            }
        }

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        when(hidden){
            false ->{

                initRequest()
            }
        }
    }

}