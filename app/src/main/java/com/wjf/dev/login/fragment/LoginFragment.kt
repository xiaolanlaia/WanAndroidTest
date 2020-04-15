package com.wjf.dev.login.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.wjf.dev.R
import com.wjf.dev.base.BaseMVVMFragment
import com.wjf.dev.databinding.LoginFragmentBinding
import com.wjf.dev.login.LoginRepository
import com.wjf.dev.login.LoginVMFactory
import com.wjf.dev.login.LoginViewModel
import kotlinx.android.synthetic.main.login_fragment.*

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 11:20
 *
 */


class LoginFragment : BaseMVVMFragment<LoginFragmentBinding, LoginViewModel>() {
    override fun initViewModel(): LoginViewModel = ViewModelProvider(this,
        LoginVMFactory(LoginRepository())
    ).get(LoginViewModel::class.java)

    override fun initContentViewID(): Int = R.layout.login_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView.vm = vm

        login_phone.addTextChangedListener(vm.phoneTextAfterChange)
        login_password.addTextChangedListener(vm.passwordTextAfterChange)
    }
}