package com.wjf.dev.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.wjf.dev.R

/**
 *       Created by xiaolanlaia on 2019/4/29 17:08
 *
 *       MVVM的Activity基类，持有一个ViewDataBind和一个ViewModel
 *
 *       使用两个抽象函数初始化这两个实例
 */
abstract class BaseMVVMActivity<VDB : ViewDataBinding, VM : ViewModel> : AppCompatActivity(), CommonMethod {

    lateinit var bindViews: VDB
    lateinit var vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews = DataBindingUtil.setContentView(this, initContentViewID())
        //bindViews=DataBindingUtil.inflate<VDB>(LayoutInflater.from(this),initContentViewID(),null,false)
        bindViews.lifecycleOwner = this
        vm = initViewModel()

        onMVVMCreated()
        fitTransparentStatus()

        if (dyeStatus()) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = getColor(R.color.colorPrimary)
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        if (isLightStatus()) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }

    abstract fun initViewModel(): VM

    open fun isLightStatus(): Boolean = false

    /**
     * 创建bindView和ViewModel后，在OnCreate方法中继续做的事情
     */
    abstract fun onMVVMCreated()

}