package com.example.myjeptdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.administrator.wanandroid.utils.runOnIoThread
import com.example.myjeptdemo.repository.WorldRepository
import com.example.myjeptdemo.room.World


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2019/12/25
 * @Description:
 */
class RoomViewModel(application: Application) : AndroidViewModel(application) {
    private var worldRepository: WorldRepository = WorldRepository(application)

    fun getAllWorldLiveData(): LiveData<List<World>> {
        return worldRepository.getAllWorldRepoData()
    }

    fun insertWorlds(world: World) {
        worldRepository.insertWorlds(world)
    }

    fun updateWorlds(world: World) {
        worldRepository.updateWorlds(world)
    }

    fun deleteWorlds(world: World) {
        worldRepository.deleteWorlds(world)
    }

    fun deleteALL() {
        worldRepository.deleteALL()
    }
}