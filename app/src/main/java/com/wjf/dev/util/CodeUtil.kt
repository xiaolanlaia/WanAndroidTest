package com.wjf.dev.util

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.SystemClock
import android.text.*
import android.view.*
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.wjf.dev.common.Constants
import com.wjf.dev.common.MyApplication
import com.wjf.dev.common.MyApplication.Companion.context
import com.wjf.dev.common.TitleWithContentActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest
import java.text.DecimalFormat
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


object CodeUtil {

    /**
     * MD5 32加密
     */
    fun encode(text: String): String {
        var instance = MessageDigest.getInstance("MD5")
        var digest = instance.digest(text.toByteArray())
        var sb = StringBuffer()
        for (b in digest) {
            var i = b.toInt() and 0xff
            var hexString = Integer.toHexString(i)
            if (hexString.length < 2) {
                hexString = "0" + hexString
            }
            sb.append(hexString)
        }
        return sb.toString()
    }


    /**
     * 字符串添加逗号
     */
    fun insertComma(oldStr: String): StringBuilder {
        // 创建一个空的StringBuilder对象
        val newStr = StringBuilder()

        newStr.append(oldStr)

        // 从后往前每隔三位插入逗号
        val last = oldStr.length
        var i = last - 3
        while (i > 0) {
            newStr.insert(i, ',')
            i -= 3
        }
        return newStr
    }

    /**
     * 保留两位小数
     */
    fun decimal(double : Double) : String{
        val df = DecimalFormat("#0.00")
        return df.format(double)
    }

    /**
     * 获取手机型号
     */
    fun getPhoneName(): String = android.os.Build.MODEL

    /**
     * 判断是否登陆
     */
    fun checkIsLogin(context: Context) : Boolean{

        if (!SharedHelper.getShared().getBoolean(Constants.SP.IS_LOGIN,false)){
            context.startActivity<TitleWithContentActivity>(
                Pair(Constants.SP.TITLE_ACTIVITY_TYPE,TitleWithContentActivity.TYPE_LOGIN)
            )
            return false
        }
        return true


    }
}

/**
 *  简化RxJava后台转前台流程
 */
fun <T> Observable<T>.doInBackground(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

/**
 * 在ViewModel中吐司的封装
 */
var lastClickTime : Long = 0L
fun ViewModel.toast(string: String?) {

    val time = SystemClock.uptimeMillis()


    if (time - lastClickTime > 500){

        var toast = Toast.makeText(MyApplication.context, null, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, -100)
        toast.setText(string)
        toast.show()

        lastClickTime = time

    }

}



fun Disposable.addTo(c: CompositeDisposable){
    c.add(this)
}


/**
 * 判断当前应用是否是debug状态
 */
fun isApkInDebug(): Boolean {
    return try {
        val info = context.applicationInfo
        info.flags and ApplicationInfo.FLAG_DEBUGGABLE !== 0
    } catch (e: Exception) {
        false
    }

}

/**
 * 获取VersionName
 */
fun getVersionName(context: Context) : String {

    val manager = context.packageManager
    try {
        val info = manager.getPackageInfo(context.packageName, PackageManager.GET_META_DATA)
        return info.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return "----"
}

/**
 * 在ViewModel中，协程的封装
 */
fun ViewModel.launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) = GlobalScope.launch(Dispatchers.Main) {
    try {
        block()
    } catch (error: Throwable) {
        error(error)
    }
}

/**
 * Retrofit返回Call配合协程的封装
 */
suspend fun<T> Call<T>.await(): T {
    return suspendCoroutine<T> {
        this.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                it.resumeWithException(t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                it.resume(response.body() as T)
            }
        })
    }
}

/**
 * 在全局中吐司的封装（无前缀）
 */
fun Context._toast(string: String?) {
    var toast = Toast.makeText(MyApplication.context, null, Toast.LENGTH_SHORT)
    toast.setText(string)
    toast.show()
}

/**
 * EditText文字监听
 */
open class SimpleTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}

