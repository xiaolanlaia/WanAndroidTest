package com.wjf.dev.main.fragment.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/13 10:37
 *
 */


class NavigationVMFactory(val repository: NavigationRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NavigationViewModel(repository) as T
    }
}