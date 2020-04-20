package com.wjf.dev.login

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wjf.dev.R
import com.wjf.dev.common.Constants
import com.wjf.dev.common.TitleWithContentActivity
import com.wjf.dev.util.SharedHelper
import com.wjf.dev.util.SimpleTextWatcher
import com.wjf.dev.util.addTo
import com.wjf.dev.util.toast
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.startActivity

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 11:20
 *
 */


class LoginViewModel(val repository: LoginRepository) : ViewModel() {

    val phone = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val repassword = MutableLiveData<String>()
    val co = CompositeDisposable()

    val loginClickListener = View.OnClickListener {



        when(it.id){

            R.id.logon ->{

                if (!checkRePassword()) return@OnClickListener

                logon(it.context)

            }

            R.id.to_logon ->{

                it.context.startActivity<TitleWithContentActivity>(
                    Pair(Constants.SP.TITLE_ACTIVITY_TYPE,TitleWithContentActivity.TYPE_LOGON)
                )

                (it.context as Activity).finish()

            }

            R.id.login_in ->{
                if (!checkEmpty()) return@OnClickListener
                loginIn(it.context)

            }
        }
    }

    /**
     * 登录
     */
    fun loginIn(context: Context){

        repository.loginIn(phone.value!!,password.value!!).subscribe({

            when(it.errorCode){

                0 ->{

                    SharedHelper.getEdit { sp -> sp.putBoolean(Constants.SP.IS_LOGIN,true) }
                    (context as Activity).finish()

                }
            }
        },{

        }).addTo(co)
    }
    /**
     * 注册
     */
    fun logon(context: Context){
        repository.loginUp(phone.value!!,password.value!!,repassword.value!!).subscribe({

            when(it.errorCode){

                0 ->{
                    loginIn(context)
                }
            }

        },{}).addTo(co)
    }

    /**
     * 退出登录
     */
    fun loginOut(){
        repository.loginOut().subscribe({

        },{

        }).addTo(co)
    }

    /**
     * 检查手机号、密码
     */
    fun checkEmpty() : Boolean{

        if (TextUtils.isEmpty(phone.value) || TextUtils.isEmpty(password.value)) {
            toast("信息不能为空")
            return false
        }

        return true
    }

    /**
     * 检查确认验证码
     */
    fun checkRePassword() : Boolean{
        if (TextUtils.isEmpty(phone.value) || TextUtils.isEmpty(password.value) || TextUtils.isEmpty(repassword.value)) {
            toast("信息不能为空")
            return false
        }

        return true
    }

    val phoneTextAfterChange = object : SimpleTextWatcher(){

        override fun afterTextChanged(s: Editable?) {
            super.afterTextChanged(s)
            phone.value = s.toString()

        }

    }

    val passwordTextAfterChange = object : SimpleTextWatcher(){

        override fun afterTextChanged(s: Editable?) {
            super.afterTextChanged(s)
            password.value = s.toString()

        }

    }

    val repasswordTextAfterChange = object : SimpleTextWatcher(){

        override fun afterTextChanged(s: Editable?) {
            super.afterTextChanged(s)
            repassword.value = s.toString()

        }

    }
}