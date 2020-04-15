package com.wjf.dev.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 11:21
 *
 */


class LoginVMFactory(val repository: LoginRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(LoginRepository()) as T
    }
}