package com.example.myjeptdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.administrator.wanandroid.utils.runOnIoThread
import com.example.myjeptdemo.room.World
import com.example.myjeptdemo.room.WorldDao
import com.example.myjeptdemo.room.WorldDataBase
import kotlinx.android.synthetic.main.activity_room_one.*

class RoomOneActivity : AppCompatActivity() {
    private lateinit var worldDao: WorldDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_one)
        worldDao = WorldDataBase.getInstence(this).getALlWorldDao()
        upddataView()
        bt_insert.setOnClickListener {
            var world1 = World(world = "hellow", chineseMean = "你好")
            var world2 = World(world = "world", chineseMean = "世界")
            worldDao.insertWorlds(world = world1)
            worldDao.insertWorlds(world = world2)
            upddataView()
        }
        //更新一项id 为32的
        bt_updata.setOnClickListener {
            var world = World(world = "hi", chineseMean = "你好被修改")
            world.id = 32
            worldDao.updateWorlds(world)
            upddataView()
        }
        //删除一项id 为35的
        bt_delect.setOnClickListener {
            var world = World(world = "hi", chineseMean = "你好被修改")
            world.id = 35
            worldDao.deleteWorlds(world)
            upddataView()
        }
        bt_clear.setOnClickListener {
            //清除所有的 但是id还是记住 递增的
            worldDao.deleteALL()
            upddataView()
        }

    }

    fun upddataView() {
        val allWorld = worldDao.getALLWorld()
        var text = ""
        allWorld?.forEach {
            text += "${it.id}:${it.world}:${it.chineseMean}\n"
        }
        text_contact.text = text
    }
}
