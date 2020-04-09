package com.wjf.dev.util

import android.content.Context
import android.view.View

/**
 *       Created by xiaolanlaia on 2019/4/30 11:05
 */
object SizeUtil {

    fun getStatusHeight(context: Context): Int {
        var statusHeightId = context.applicationContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        var statusHeight = context.applicationContext.resources.getDimensionPixelSize(statusHeightId)
        return statusHeight
    }
}

/**
 * 使View适配透明状态栏
 *
 * 将该View的高度+状态栏高度
 */
fun View.fitTransparentStatus() {
    var statusHeight = SizeUtil.getStatusHeight(this.context)
    var lp = this.layoutParams
    layoutParams.height += statusHeight
    this.layoutParams = lp
}