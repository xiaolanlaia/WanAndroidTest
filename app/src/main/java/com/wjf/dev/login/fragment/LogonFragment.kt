package com.wjf.dev.login.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.databinding.LoginFragmentLogonBinding
import com.wjf.dev.login.LoginRepository
import com.wjf.dev.login.LoginVMFactory
import com.wjf.dev.login.LoginViewModel
import kotlinx.android.synthetic.main.login_fragment_logon.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 15:21
 *
 */


class LogonFragment : BaseMVVMFragment<LoginFragmentLogonBinding, LoginViewModel>() {
    override fun initViewModel(): LoginViewModel = ViewModelProvider(this,
        LoginVMFactory(LoginRepository())
    ).get(LoginViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.login_fragment_logon

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm

        login_phone.addTextChangedListener(vm.phoneTextAfterChange)
        login_password.addTextChangedListener(vm.passwordTextAfterChange)
        login_repassword.addTextChangedListener(vm.repasswordTextAfterChange)
    }
}