package com.example.myjeptdemo.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2019/12/23
 * @Description:
 */
class MyViewModel : ViewModel() {
    /**
     * 默认初始值为0
     * 退出此界面也可保存，类似于flutter的provide 可实时保存
     */
    private var mATeamLiveData = MutableLiveData<Int>(0)
    private var mBTeamLiveData = MutableLiveData<Int>(0)

    fun getmATeamLiveData(): MutableLiveData<Int> {
        return mATeamLiveData
    }

    fun getmBTeamLiveData(): MutableLiveData<Int> {
        return mBTeamLiveData
    }

    fun addmATeam() {
        mATeamLiveData.value = mATeamLiveData?.value!! + 1
    }

    fun addmBeam() {
        mBTeamLiveData.value = mBTeamLiveData?.value!! + 1

    }
}