package com.example.baseproject.util

import android.content.SharedPreferences
import com.example.baseproject.common.MyApplication
import org.jetbrains.anko.defaultSharedPreferences

/**
 *       Created by xiaolanlaia on 2019/5/6 13:44
 *
 *       SharedPreference 帮助工具类
 */
object SharedHelper {

    fun getEdit(edit: (editor: SharedPreferences.Editor) -> SharedPreferences.Editor) {
        edit(MyApplication.context.defaultSharedPreferences.edit()).commit()
    }

    fun getShared(): SharedPreferences {
        return MyApplication.context.defaultSharedPreferences
    }
}