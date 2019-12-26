package com.example.myjeptdemo.room

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2019/12/24
 * @Description:
 */
@Dao
interface WorldDaoLiveData {
    @Insert
    fun insertWorlds(world: World)

    @Update
    fun updateWorlds(world: World)

    @Delete
    fun deleteWorlds(world: World)

    @Query("DELETE FROM World")
    fun deleteALL()

    @Query("SELECT * FROM World ORDER BY ID DESC")
    //LiveData获取
    fun getALLWorld(): LiveData<List<World>>
}