package com.wjf.dev.collect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/15 16:44
 *
 */


class CollectVMFactory(val repository: CollectRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CollectViewModel(repository) as T
    }
}