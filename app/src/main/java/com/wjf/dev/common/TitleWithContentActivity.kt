package com.wjf.dev.common

import android.text.TextUtils
import com.wjf.dev.R
import com.wjf.dev.webview.WebFragment
import com.wjf.dev.base.BaseActivity
import com.wjf.dev.main.fragment.mine.collect.CollectFragment
import com.wjf.dev.login.fragment.LoginFragment
import com.wjf.dev.login.fragment.LogonFragment
import com.wjf.dev.main.fragment.mine.integral.IntegralFragment
import com.wjf.dev.main.fragment.mine.rank.RankFragment
import com.wjf.dev.main.fragment.mine.setting.SettingFragment
import com.wjf.dev.userArticle.ArticleSortFragment
import com.wjf.dev.userArticle.AuthorArticleFragment
import kotlinx.android.synthetic.main.activity_title_with_content.*


/**
 * Title + Content 格式的Activity
 *
 */
class TitleWithContentActivity : BaseActivity() {

    var type = 0

    override fun initContentViewID(): Int = R.layout.activity_title_with_content

    override fun isLightStatus(): Boolean = true

    override fun onViewCreated() {


        type = intent.getIntExtra(Constants.SP.TITLE_ACTIVITY_TYPE, 0)

        val transaction = supportFragmentManager.beginTransaction()
        val url = intent.getStringExtra(Constants.SP.URL)
        when (type) {
            TYPE_WEB_VIEW -> {
                if (!TextUtils.isEmpty(intent.getStringExtra(Constants.SP.WEBVIEW_TITLE))){

                    info_toolbar.setTitle(intent.getStringExtra(Constants.SP.WEBVIEW_TITLE))
                }
                transaction.replace(R.id.info_content, WebFragment.newInstance(url)).commit()
            }

            TYPE_USER_ARTICLE_LIST ->{

                if (!TextUtils.isEmpty(intent.getStringExtra(Constants.SP.AUTHOR_NAME))){

                    info_toolbar.setTitle(intent.getStringExtra(Constants.SP.AUTHOR_NAME))
                }
                transaction.replace(R.id.info_content, AuthorArticleFragment.newInstance(intent.getIntExtra(Constants.SP.AUTHOR_ID,-1))).commit()

            }

            TYPE_ARTICLE_SORT_LIST ->{
                if (!TextUtils.isEmpty(intent.getStringExtra(Constants.SP.ARTICLE_TITLE))){

                    info_toolbar.setTitle(intent.getStringExtra(Constants.SP.ARTICLE_TITLE))
                }
                transaction.replace(R.id.info_content, ArticleSortFragment.newInstance(intent.getIntExtra(Constants.SP.CID,-1))).commit()

            }

            TYPE_LOGIN ->{
                transaction.replace(R.id.info_content, LoginFragment()).commit()

            }
            TYPE_LOGON ->{
                transaction.replace(R.id.info_content, LogonFragment()).commit()

            }
            TYPE_MINE_COLLECT ->{
                info_toolbar.setTitle("我的收藏")
                transaction.replace(R.id.info_content, CollectFragment()).commit()
            }
            TYPE_MINE_INTEGRAL ->{
                info_toolbar.setTitle("积分记录")
                transaction.replace(R.id.info_content, IntegralFragment()).commit()
            }
            TYPE_MINE_INTEGRAL_RANK ->{
                info_toolbar.setTitle("积分排行")
                transaction.replace(R.id.info_content, RankFragment()).commit()
            }
            TYPE_MINE_INTEGRAL_SETTING ->{
                info_toolbar.setTitle("设置")
                transaction.replace(R.id.info_content, SettingFragment()).commit()
            }

        }
    }

    override fun fitTransparentStatus() {
        info_toolbar.fitTransparentStatus()
    }



    /**
     * 内容
     */
    companion object {

        const val TYPE_WEB_VIEW = 0
        const val TYPE_USER_ARTICLE_LIST = 1
        const val TYPE_ARTICLE_SORT_LIST = 2
        const val TYPE_LOGIN = 3
        const val TYPE_LOGON = 4
        const val TYPE_MINE_COLLECT = 5
        const val TYPE_MINE_INTEGRAL = 6
        const val TYPE_MINE_INTEGRAL_RANK = 7
        const val TYPE_MINE_INTEGRAL_SETTING = 8

    }
}
