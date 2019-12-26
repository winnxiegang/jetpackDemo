package com.example.myjeptdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myjeptdemo.ViewModelSaveActivity

class ViewModelSave(var handle: SavedStateHandle) : ViewModel() {

    /**
     * 默认初始值为0
     * 退出此界面也可保存，类似于flutter的provide 可实时保存
     */
    fun getmATeamLiveData(): MutableLiveData<Int> {
        if (!handle.contains(ViewModelSaveActivity.saveKey)) {
            handle.set(ViewModelSaveActivity.saveKey, 0)
        }
        return handle.getLiveData(ViewModelSaveActivity.saveKey)
    }


    fun addmATeam() {
        getmATeamLiveData().value = getmATeamLiveData()?.value!! + 1

    }

}