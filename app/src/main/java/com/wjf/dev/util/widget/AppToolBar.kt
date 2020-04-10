package com.wjf.dev.util.widget

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.wjf.dev.R
import com.wjf.dev.util.SizeUtil
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.textColor

/**
 *       Created by xiaolanlaia on 2019/4/29 19:24
 *
 *       自定义简单的ToolBar，并且对其操作进行简单的封装
 */
class AppToolBar : RelativeLayout {

    //ToolBar
    private lateinit var view: View
    //中间的标题
    private lateinit var title: TextView
    //返回键
    private lateinit var back: ImageView
    //客服
    private lateinit var kefu: ImageView
    //消息
    private lateinit var message: ImageView
    //status高度
    private lateinit var status: View

    var style = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        view = LayoutInflater.from(context).inflate(R.layout.include_toolbar, this)
        status = view.findViewById(R.id.toolbar_status_height)
        title = view.findViewById(R.id.toolbar_title)
        back = view.findViewById(R.id.toolbar_back)
        kefu = view.findViewById(R.id.toolbar_kefu)
        message = view.findViewById(R.id.toolbar_message)
        //获取自定义属性
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.AppToolBar)
        val titleText = typeArray.getString(R.styleable.AppToolBar_toolbar_title)
        //赋值
        title.text = titleText
        style = typeArray.getInt(R.styleable.AppToolBar_toolbar_style, 0)
        when (style) {
            0 -> showHomePage()
            1 -> showBackAndTitle()
            2 -> showOnlyTitle()
            3 -> showVipTitle()
            4 -> showOnlyBack()
            5 -> showLoginTitle()
            6 -> showBlackColor()
        }
        //默认设置back点击事件为finish
        back.setOnClickListener {
            context as Activity
            context.finish()
        }
        typeArray.recycle()
    }

    private fun showBlackColor() {
        title.visibility = View.VISIBLE
        back.visibility = View.VISIBLE
        message.visibility = View.INVISIBLE
        kefu.visibility = View.INVISIBLE
        title.setTextColor(context.getColor(R.color.color_333333))
    }

    fun setTitleStyle(style : Int){
        this.style = style
    }



    fun showHomePage() {
        title.visibility = View.VISIBLE
        title.paint.isFakeBoldText = true
        title.text = context.getString(R.string.app_name)
        title.textColor = Color.WHITE
        back.visibility = View.INVISIBLE
        message.visibility = View.INVISIBLE
        kefu.visibility = View.INVISIBLE
        backgroundColor = Color.TRANSPARENT
    }

    fun showBackAndTitle() {
        title.visibility = View.VISIBLE
        back.visibility = View.VISIBLE
        kefu.visibility = View.INVISIBLE
        message.visibility = View.INVISIBLE
        backgroundColor = Color.WHITE
    }

    fun showLoginTitle(){
        title.visibility = View.VISIBLE
        title.textColor = view.context.getColor(R.color.color_white)
        back.visibility = View.VISIBLE
        back.setImageResource(R.mipmap.back_bai)
        kefu.visibility = View.INVISIBLE
        message.visibility = View.INVISIBLE
        backgroundColor = Color.TRANSPARENT
    }

    fun showOnlyTitle() {
        title.visibility = View.VISIBLE
        back.visibility = View.INVISIBLE
        kefu.visibility = View.INVISIBLE
        message.visibility = View.INVISIBLE
        backgroundColor = Color.WHITE
    }

    fun showVipTitle(){
        title.visibility = View.VISIBLE
        title.textColor = Color.WHITE
        back.visibility = View.VISIBLE
        kefu.visibility = View.INVISIBLE
        message.visibility = View.INVISIBLE
        backgroundColor = Color.TRANSPARENT
    }

    fun showOnlyBack(){

        title.visibility = View.INVISIBLE
        back.visibility = View.VISIBLE
        kefu.visibility = View.INVISIBLE
        message.visibility = View.INVISIBLE
        backgroundColor = Color.WHITE
    }

    fun setTitle(text: String) {
        title.text = text
    }

    fun getTitle() : String{

        return title.text.toString()
    }

    fun setBackOnclickListener(listener: OnClickListener) {
        back.setOnClickListener(listener)
    }


    fun setKefuOnclickListener(listener: OnClickListener) {
        kefu.setOnClickListener(listener)
    }

    fun setMessageOnclickListener(listener: OnClickListener) {
        message.setOnClickListener(listener)
    }

    fun setMessageImage(id: Int) {
        message.setImageResource(id)
    }

    /**
     * 适应透明状态栏
     */
    fun fitTransparentStatus() {
        //增加statusView的高度
        var statusHeight = SizeUtil.getStatusHeight(context)
        var statusLayoutParams = status.layoutParams
        statusLayoutParams.height = statusHeight
        status.layoutParams = statusLayoutParams
        //增加整体ToolBar的高度
        var viewLayoutParams = view.layoutParams
        viewLayoutParams.height += statusHeight
        view.layoutParams = viewLayoutParams
    }

    /**
     *================↓↓↓=DataBinding 相关=↓↓↓===================
     */
    companion object {
        /**
         * 绑定客服点击事件
         */
        @BindingAdapter("kefuOnclick")
        @JvmStatic
        fun setKefuOnclick(view: AppToolBar, listener: View.OnClickListener) {
            view.setKefuOnclickListener(listener)
        }

        /**
         * 绑定消息点击事件
         */
        @BindingAdapter("messageOnclick")
        @JvmStatic
        fun setMessageOnclick(view: AppToolBar, listener: View.OnClickListener) {
            view.setMessageOnclickListener(listener)
        }
    }
}

