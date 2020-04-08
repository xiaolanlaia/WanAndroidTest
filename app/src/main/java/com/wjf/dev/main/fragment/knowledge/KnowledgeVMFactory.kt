package com.wjf.dev.main.fragment.knowledge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  @author  xiaolanlaia
 *
 *  @create  2020/4/8 14:12
 *
 */


class KnowledgeVMFactory(val repository: KnowledgeRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KnowledgeViewModel(repository) as T
    }
}