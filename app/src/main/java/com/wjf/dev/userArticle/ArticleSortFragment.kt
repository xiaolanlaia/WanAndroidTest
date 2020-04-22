package com.wjf.dev.userArticle

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.databinding.FragmentAuthorArticleBinding
import com.wjf.dev.main.fragment.home.adapter.HomeArticleAdapter
import kotlinx.android.synthetic.main.fragment_author_article.*
import kotlinx.android.synthetic.main.home_fragment_recycler_item.*
import org.jetbrains.anko.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/10 13:59
 *
 */


class ArticleSortFragment : BaseMVVMFragment<FragmentAuthorArticleBinding, AuthorArticleViewModel>() {

    companion object{


        fun newInstance(cid : Int) : ArticleSortFragment {

            val fragment = ArticleSortFragment()
            val bundle = Bundle()
            bundle.putInt(Constants.SP.CID,cid)
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun initViewModel(): AuthorArticleViewModel =
        ViewModelProvider(this,AuthorArticleVMFactory(AuthorArticleRepository())).get(AuthorArticleViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.fragment_author_article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm

        initRequest()

        //设置layoutManager
        author_article_recycler.layoutManager = LinearLayoutManager(context)

        val homeArticleAdapter = HomeArticleAdapter()
        author_article_recycler.adapter = homeArticleAdapter



        vm.articleSortList.observe(viewLifecycleOwner, Observer {

            homeArticleAdapter.replaceData(it)

        })


        homeArticleAdapter.setOnItemClickListener(object : HomeArticleAdapter.OnItemClickListener {

            override fun onItemClick(id: Int, collect: Boolean) {

                when (collect) {

                    true -> {

                        vm.unCollect(id)
                    }

                    false -> {
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



    fun initRequest(){


        vm.getArticleSort(arguments!!.getInt(Constants.SP.CID,-1))

    }
}