package com.wjf.dev.common

import com.wjf.dev.R
import com.wjf.dev.webview.WebFragment
import com.wjf.dev.base.BaseActivity
import kotlinx.android.synthetic.main.activity_title_with_content.*


/**
 * Title + Content 格式的Activity
 *
 */
class TitleWithContentActivity : BaseActivity() {

    var type = 0

    var returnToMainPage = false
    var returnToWhere = 0

    override fun initContentViewID(): Int = R.layout.activity_title_with_content

    override fun isLightStatus(): Boolean = true

    override fun onViewCreated() {

        //设置ToolBar返回键监听
//        info_toolbar.setBackOnclickListener(View.OnClickListener {
//
//
//        })

        type = intent.getIntExtra(Constants.SP.TITLE_ACTIVITY_TYPE, 0)

        val transaction = supportFragmentManager.beginTransaction()
        val url = intent.getStringExtra(Constants.SP.URL)
        when (type) {
            TYPE_WEB_VIEW -> {
                info_toolbar.setTitle(intent.getStringExtra(Constants.SP.WEBVIEW_TITLE))
                transaction.replace(R.id.info_content, WebFragment.newInstance(url)).commit()
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

    }
}
