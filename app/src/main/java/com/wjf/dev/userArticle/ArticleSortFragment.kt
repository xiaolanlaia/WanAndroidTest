package com.wjf.dev.userArticle

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.common.Constants
import com.wjf.dev.databinding.FragmentAuthorArticleBinding
import com.wjf.dev.userArticle.adapter.ArticleSortAdapter
import kotlinx.android.synthetic.main.fragment_author_article.*

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

        val articleSortAdapter = ArticleSortAdapter()
        author_article_recycler.adapter = articleSortAdapter



        vm.articleSortList.observe(viewLifecycleOwner, Observer {

            articleSortAdapter.replaceData(it)

        })


        articleSortAdapter.setOnItemClickListener(object : ArticleSortAdapter.OnItemClickListener {
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
        })

    }



    fun initRequest(){


        vm.getArticleSort(arguments!!.getInt(Constants.SP.CID,-1))

    }
}