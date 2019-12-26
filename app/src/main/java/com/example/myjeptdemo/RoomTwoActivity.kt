package com.example.myjeptdemo

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.administrator.wanandroid.utils.runOnIoThread
import com.example.myjeptdemo.room.World
import com.example.myjeptdemo.room.WorldDaoLiveData
import com.example.myjeptdemo.room.WorldDataBase
import kotlinx.android.synthetic.main.activity_room_two.*

class RoomTwoActivity : AppCompatActivity() {
    private lateinit var worldDaoLiveData: WorldDaoLiveData
    private lateinit var liveData: LiveData<List<World>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_two)
        worldDaoLiveData = WorldDataBase.getInstence(this).getALlWorldDaoLiveData()
        liveData = worldDaoLiveData.getALLWorld()

        //todo liveData 可以进行动态观察实时刷新数据
        liveData.observe(this, Observer<List<World>> {
            var text = ""
            it?.forEach {
                text += "${it.id}:${it.world}:${it.chineseMean}\n"
            }
            text_contact.text = text
        })
        bt_insert.setOnClickListener {
            var world1 = World(world = "hellow", chineseMean = "你好")
            var world2 = World(world = "world", chineseMean = "世界")
            InsertAsyncTask(worldDaoLiveData).execute(world1, world2)
        }
        //更新一项id 为32的
        bt_updata.setOnClickListener {
            var world = World(world = "hi", chineseMean = "你好被修改")
            world.id = 66
            UpdataAsyncTask(worldDaoLiveData).execute(world)
        }
        //删除一项id 为35的
        bt_delect.setOnClickListener {
            var world = World(world = "hi", chineseMean = "你好被修改")
            world.id = 66
            DelectAsyncTask(worldDaoLiveData).execute(world)
        }
        bt_clear.setOnClickListener {
            //清除所有的 但是id还是记住 递增的
            //  ClearAsyncTask(worldDaoLiveData).execute()
            runOnIoThread { worldDaoLiveData.deleteALL() }
        }
    }

    //todo 必须异步进行操作相当于==runOnIoThread
    class InsertAsyncTask(private val worldDaoLiveData: WorldDaoLiveData) :
        AsyncTask<World, Any, Any>() {
        override fun doInBackground(vararg params: World?): Any {
            worldDaoLiveData.insertWorlds(world = params[0]!!)
            return 0
        }
    }

    class UpdataAsyncTask(private val worldDaoLiveData: WorldDaoLiveData) :
        AsyncTask<World, Any, Any>() {
        override fun doInBackground(vararg params: World?): Any {
            worldDaoLiveData.updateWorlds(world = params[0]!!)
            return 0
        }
    }

    class DelectAsyncTask(private val worldDaoLiveData: WorldDaoLiveData) :
        AsyncTask<World, Any, Any>() {
        override fun doInBackground(vararg params: World?): Any {
            worldDaoLiveData.deleteWorlds(world = params[0]!!)
            return 0
        }
    }

    class ClearAsyncTask(private val worldDaoLiveData: WorldDaoLiveData) :
        AsyncTask<World, Any, Any>() {
        override fun doInBackground(vararg params: World?): Any {
            worldDaoLiveData.deleteALL()
            return 0
        }
    }
}
