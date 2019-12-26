package com.example.myjeptdemo.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.example.myjeptdemo.ViewModelActivityActivity

/**
 * @Author: xiegang
 * @CreateDate: 2019/12/23
 * @Description:
 */
class AndrViewModel(private val handle: SavedStateHandle, application: Application) :
    AndroidViewModel(application) {
    private var mApplication: Application = application

    init {
        if (!handle.contains(ViewModelActivityActivity.dataKey)) {
            loadData()
        }
    }

    fun loadData() {
        var sharedPreferences = mApplication?.getSharedPreferences(
            ViewModelActivityActivity.dataKey, Context.MODE_PRIVATE
        )
        var x: Int = sharedPreferences.getInt(ViewModelActivityActivity.dataKey, 0)
        handle.set(ViewModelActivityActivity.dataKey, x)
    }
}