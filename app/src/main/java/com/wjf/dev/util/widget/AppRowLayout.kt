package com.wjf.dev.util.widget

import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.wjf.dev.R

/**
 *       Created by xiaolanlaia on 2019/4/29 15:23
 */
class AppRowLayout : RelativeLayout {

    lateinit var view: View
    lateinit var imageLeft: ImageView
    lateinit var title: TextView
    lateinit var content: TextView
    lateinit var imageRight: ImageView
    lateinit var rightText: TextView

    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        view = LayoutInflater.from(context).inflate(R.layout.item_row, this)
        //找到布局中的三个控件
        imageLeft = view.findViewById(R.id.row_left_iv)
        title = view.findViewById(R.id.row_title_tv)
        content = view.findViewById(R.id.row_content_tv)
        imageRight = view.findViewById(R.id.row_right_iv)
        rightText = view.findViewById(R.id.row_right_content_tv)
        //获取属性
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.AppRowLayout)
        val imageLeftId = typeArray.getResourceId(R.styleable.AppRowLayout_image_left, R.mipmap.ic_launcher)
        val textTitle = typeArray.getString(R.styleable.AppRowLayout_row_title_text)
        val textContent = typeArray.getString(R.styleable.AppRowLayout_row_content_text)
        val imageRightId = typeArray.getResourceId(R.styleable.AppRowLayout_image_right, R.mipmap.icon_arrow_right)

        //风格属性
        val rowStyle = typeArray.getInteger(R.styleable.AppRowLayout_row_style, 0)
        val rightTextStyle = typeArray.getInteger(R.styleable.AppRowLayout_row_right_text_style, 0)
        //设置风格
        if (rowStyle == 0) {
            //右边为箭头+文字风格
            setArrowWithTextStyle()
        } else {
            //右边为圆角文字风格
            setRightTextStyle()
        }

        if (rightTextStyle == 0) {
            //灰底未验证状态
//            setAuthNo()
        } else {
            //蓝底已验证状态
//            setAuthYes()
        }
        //属性赋值
        imageLeft.setImageResource(imageLeftId)
        title.text = textTitle
        content.text = textContent
        imageRight.setImageResource(imageRightId)

        typeArray.recycle()
    }

//    fun setAuthNo() {
//        rightText.text = "未验证"
//        rightText.textColor= context.getColor(R.color.auth_btn_yes)
//        rightText.background = context.getDrawable(R.drawable.shape_auth_btn_no)
//    }

//    fun setAuthYes() {
//        rightText.text = "已验证"
//        rightText.textColor= Color.WHITE
//        rightText.background = context.getDrawable(R.drawable.item_borrow_state_check_shape)
//    }

    private fun setRightTextStyle() {
        imageRight.visibility = View.GONE
        content.visibility = View.GONE
        rightText.visibility = View.VISIBLE
    }

    private fun setArrowWithTextStyle() {
        imageRight.visibility = View.VISIBLE
        content.visibility = View.VISIBLE
        rightText.visibility = View.GONE
    }

    /**
     *================↓↓↓=DataBinding 相关=↓↓↓===================
     */
    companion object {
        /**
         * 绑定是否认证的状态
         */
        @BindingAdapter("app:row_right_text_style")
        @JvmStatic
        fun setAuthState(view: AppRowLayout, state: Int) {
//            if (state == 1) {
//                view.setAuthYes()
//            } else {
//                view.setAuthNo()
//            }
        }
    }
}