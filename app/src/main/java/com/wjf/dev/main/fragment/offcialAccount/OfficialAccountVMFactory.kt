package com.wjf.dev.main.fragment.offcialAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:12
 *
 */


class OfficialAccountVMFactory(val repository: OfficialAccountRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OfficialAccountViewModel(repository) as T
    }
}