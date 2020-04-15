package com.wjf.dev.login

import com.sun.dev.entity.BaseBean
import com.wjf.dev.entity.LoginBean
import com.wjf.dev.util.RetrofitManager
import com.wjf.dev.util.doInBackground
import io.reactivex.Observable

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 11:21
 *
 */


class LoginRepository {
    /**
     * 登录
     */
    fun loginIn(username : String,password : String) : Observable<LoginBean>{

        return RetrofitManager.loginIn(username,password).doInBackground()
    }

    /**
     * 注册
     */
    fun loginUp(username : String,password : String,repassword : String) :Observable<BaseBean>{

        return RetrofitManager.loginUp(username,password,repassword).doInBackground()
    }

    /**
     * 退出登录
     */
    fun loginOut() : Observable<BaseBean>{
        return RetrofitManager.loginOut().doInBackground()
    }
}