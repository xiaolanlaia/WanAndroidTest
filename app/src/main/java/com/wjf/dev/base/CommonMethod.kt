package com.wjf.dev.base

/**
 *       Created by xiaolanlaia on 2019/5/3 16:09
 */
interface CommonMethod {
    fun initContentViewID(): Int
    /**
     * 适配透明状态栏
     */
    open fun fitTransparentStatus() {}

    /**
     * 使状态栏为主题色  默认=false
     */
    open fun dyeStatus(): Boolean = false

}