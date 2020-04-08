package com.wjf.dev.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.wjf.dev.R

/**
 *       Created by xiaolanlaia on 2019/4/30 14:08
 */
abstract class BaseFragment : Fragment(), CommonMethod {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(initContentViewID(), container, false)
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
}