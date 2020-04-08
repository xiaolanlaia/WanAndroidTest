package com.wjf.dev.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.wjf.dev.R

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/1/11 11:52
 *
 */



abstract class BaseMVVMDialogFragment<VSD : ViewDataBinding, VM : ViewModel> : DialogFragment(), CommonMethod {

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