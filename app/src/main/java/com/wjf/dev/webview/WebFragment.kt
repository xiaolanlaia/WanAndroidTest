package com.wjf.dev.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.TextView
import com.wjf.dev.R
import com.wjf.dev.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_web.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/9 14:21
 *
 */


class WebFragment : BaseFragment(){
    override fun initContentViewID(): Int = R.layout.fragment_web

    companion object{

        fun newInstance(url : String) : WebFragment {

            val fragment = WebFragment()
            val bundle = Bundle()
            bundle.putString("url",url)
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView(arguments!!.getString("url"))
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(url : String?) {
        //通过Webview 创建出WebSettings
        val settings = web_view.settings
        //设置的WebView用户代理字符串
        settings.userAgentString = ""
        //启用JavaScript
        settings.javaScriptEnabled = true
        //启用插件
        settings.pluginState = WebSettings.PluginState.ON
        //让JavaScript自动打开窗口
        settings.javaScriptCanOpenWindowsAutomatically = true
        //设置DOM存储API是否已启用
        settings.domStorageEnabled = true
        //设置是否从网络web视图不应加载图像资源（通过http和https URI方案访问的资源）
        settings.blockNetworkImage = false
        //套的WebView是否应使用其内置的缩放机制
        settings.builtInZoomControls = true
        //设置的WebView是否应该保存的表单数据
        settings.saveFormData = true
        //这种方法在API层面18.保存密码中的WebView不会在将来的版本中支持已被否决
        settings.savePassword = true
        //设置使用内置变焦机制，当web视图是否应显示在屏幕缩放控制
        settings.displayZoomControls = true
        //设置是否WebView中是否支持多个窗口
        settings.setSupportMultipleWindows(true)
        //设置的WebView是否需要用户手势媒体播放
        settings.mediaPlaybackRequiresUserGesture = true
        //去掉放大缩小按钮
        settings.displayZoomControls = false


        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        val titleTv = activity!!.findViewById<TextView>(R.id.toolbar_title)

//        web_view.webChromeClient = object : WebChromeClient() {
//            override fun onReceivedTitle(view: WebView, title: String) {
//                super.onReceivedTitle(view, title)
//                if (!TextUtils.isEmpty(title)){
//
//                    titleTv!!.text = title
//                }
//
//            }
//        }


        //如果不设置WebViewClient，请求会跳转系统浏览器
        web_view.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, urlString: String): Boolean {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                return false
            }

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                return false
            }
        }
        web_view.loadUrl(url)
    }
}