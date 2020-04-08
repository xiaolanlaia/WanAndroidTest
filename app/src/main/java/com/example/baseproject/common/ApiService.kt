package com.example.baseproject.common

import com.sun.dev.entity.*
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

/**
 *       Created by xiaolanlaia on 2019/5/6 13:40
 */
interface ApiService {

    /**
     * 获取手机验证码
     */
    @POST(Constants.URL.PHONE_CODE)
    fun getPhoneCode(@Body body: RequestBody):Observable<BaseBean>

}