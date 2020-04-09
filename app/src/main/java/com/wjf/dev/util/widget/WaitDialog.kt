package com.wjf.dev.util.widget

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import com.wjf.dev.R


/**
 *       Created by xiaolanlaia on 2019/5/11 16:28
 */
class WaitDialog : Dialog {

    lateinit var textView :TextView

    constructor(context: Context) : this(context, R.style.AlertDialog_WaitProgress)
    constructor(context: Context, themeResId: Int) : super(context, themeResId){
        setContentView(R.layout.dialog_progress)
        textView = findViewById(R.id.progress_content)
        //默认不能手动退出
        setCancelable(false)

        //背景改透明
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        //去除半透明阴影
        val layoutParams = window!!.attributes
        layoutParams.dimAmount = 0.0f
        window!!.attributes = layoutParams

    }
}