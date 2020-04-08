package com.wjf.dev.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import androidx.lifecycle.ViewModel
import com.wjf.dev.R

/**
 *       Created by xiaolanlaia on 2019/4/29 17:15
 *
 *       MVVM的Fragment基类，持有一个ViewDataBind和一个ViewModel
 *
 *       使用两个抽象函数初始化这两个实例
 */
abstract class BaseMVVMFragment<VSD : ViewDataBinding, VM : ViewModel> : Fragment(), CommonMethod {

    lateinit var bindView: VSD
    lateinit var vm: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(initContentViewID(), container, false)
        bindView = DataBindingUtil.bind(v)!!
        vm = initViewModel()
        bindView.lifecycleOwner = this
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fitTransparentStatus()
        //设置状态背景栏颜色
            if (dyeStatus()) {
                activity?.let {
                    it.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    it.window.statusBarColor = it.getColor(R.color.colorPrimary)
                }
            } else {
            activity?.let {
                it.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
        }
    }



    abstract fun initViewModel(): VM

}