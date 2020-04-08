package com.example.baseproject.common

import com.example.baseproject.BuildConfig


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
        //盐
        val YAN = "tt^hz"
        //用户相关
        const val USER_PRE = "bsd"
        const val USER = "$USER_PRE/api/"



        //获取验证码
        const val PHONE_CODE = USER + "sendMsg"





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






    }


    object Page {

        const val PAGE_AUTHENTICATION = "page_authentication"

    }

}
