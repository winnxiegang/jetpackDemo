package com.example.myjeptdemo.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.administrator.wanandroid.utils.runOnIoThread
import com.example.myjeptdemo.room.World
import com.example.myjeptdemo.room.WorldDataBase


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2019/12/25
 * @Description: 仓库 数据的获取 上传 在这里处理
 */
class WorldRepository(context: Context) {
    private var worldDaoLiveData = WorldDataBase.getInstence(context).getALlWorldDaoLiveData()
    private var allWorldLiveData: LiveData<List<World>>

    init {
        allWorldLiveData = worldDaoLiveData.getALLWorld()
    }

    fun getAllWorldRepoData(): LiveData<List<World>> {
        return allWorldLiveData
    }

    fun insertWorlds(world: World) {
        runOnIoThread { worldDaoLiveData.insertWorlds(world) }
    }

    fun updateWorlds(world: World) {
        runOnIoThread { worldDaoLiveData.updateWorlds(world) }
    }

    fun deleteWorlds(world: World) {
        runOnIoThread { worldDaoLiveData.deleteWorlds(world) }
    }

    fun deleteALL() {
        runOnIoThread { worldDaoLiveData.deleteALL() }
    }

}