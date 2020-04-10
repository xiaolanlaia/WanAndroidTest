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
import com.wjf.dev.entity.AuthorArticleBean
import com.wjf.dev.userArticle.adapter.AuthorArticleAdapter
import kotlinx.android.synthetic.main.fragment_author_article.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/10 8:59
 *
 */


class AuthorArticleFragment : BaseMVVMFragment<FragmentAuthorArticleBinding,AuthorArticleViewModel>() {

    companion object{


        fun newInstance(userId : Int) : AuthorArticleFragment {

            val fragment = AuthorArticleFragment()
            val bundle = Bundle()
            bundle.putInt(Constants.SP.AUTHOR_ID,userId)
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

        val authorArticleAdapter = AuthorArticleAdapter()
//        homeAdapter.setOnItemChildClickListener(this)
        author_article_recycler.adapter = authorArticleAdapter




        vm.articleList.observe(viewLifecycleOwner, Observer {

            authorArticleAdapter.updateList(it as MutableList<AuthorArticleBean.DataBean.ShareArticlesBean.DatasBean>)

        })

    }

    fun initRequest(){

        vm.getAuthorArticleList(arguments!!.getInt(Constants.SP.AUTHOR_ID,-1))

    }
}