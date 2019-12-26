package com.example.myjeptdemo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2019/12/24
 * @Description:
 */
@Entity
data class World(
    @ColumnInfo(name = "english_world") var world: String? = null,
    @ColumnInfo(name = "chinese_world") var chineseMean: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}