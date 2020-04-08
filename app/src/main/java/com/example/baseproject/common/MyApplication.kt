package com.example.baseproject.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.baseproject.BuildConfig
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

/**
 *       Created by xiaolanlaia on 2019/5/6 13:43
 */
class MyApplication : Application() {
    //线上(请替换客户自己的AppID和secret)
    private val LF_APP_ID = "eef48772db2a41aab9eac817a1d716f1"
    private val LF_APP_SECRET = "8846c247c0d644faa4411e13d48b35cf"
    override fun onCreate() {
        super.onCreate()
        //初始化全局Context
        context = this.applicationContext
        /**
         * 注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，
         * 也需要在App代码中调用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
         * UMConfigure.init调用中appkey和channel参数请置为null）。
         *
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:【友盟+】 AppKey
         * 参数3:【友盟+】 Channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret,不使用直接传空
         */
        UMConfigure.init(
            this,
            BuildConfig.UMENG_APP_KEY,
            BuildConfig.UMENG_CHANNEL,
            UMConfigure.DEVICE_TYPE_PHONE,
            ""
        )
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(false)

        /**
         * 选用AUTO页面采集模式
         * Android 4.0 以上推荐使用
         */
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)


    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}