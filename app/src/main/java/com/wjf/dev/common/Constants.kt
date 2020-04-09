package com.wjf.dev.common

import com.wjf.dev.BuildConfig


/**
 * 常量
 */
internal interface Constants {

    /**
     * 网络请求相关
     */
    object URL {
        // Base_Url
        const val BASE_URL = BuildConfig.ApiHost


        //首页banner
        const val GET_HOME_BANNER_DATA = "banner/json"
        //首页文章列表
        const val GET_HOME_ARTICLE_LIST = "article/list/0/json"






    }

    /**
     * SharedPreference相关
     */
    object SP {
        //TitleWithContentActivity内容的类型
        const val TITLE_ACTIVITY_TYPE = "content_type"
        //用户是否登陆
        const val IS_LOGIN = "is_login"
        //Token
        const val TOKEN = "token"
        //
        const val URL = "url"
        const val WEBVIEW_TITLE = "webview_title"






    }

    /**
     * 跟数据相关
     */
    object DATA {

        const val LOGIN_FAIL = -1001
    }


    object Page {

        const val PAGE_AUTHENTICATION = "page_authentication"

    }

}
