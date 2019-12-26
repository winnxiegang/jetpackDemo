package com.example.myjeptdemo

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.administrator.wanandroid.utils.runOnIoThread
import com.example.myjeptdemo.room.World
import com.example.myjeptdemo.room.WorldDaoLiveData
import com.example.myjeptdemo.room.WorldDataBase
import com.example.myjeptdemo.viewmodel.MyViewModel
import com.example.myjeptdemo.viewmodel.RoomViewModel
import kotlinx.android.synthetic.main.activity_room_two.*

class RoomViewModelActivity : AppCompatActivity() {
    private lateinit var roomViewModel: RoomViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_two)
        roomViewModel = ViewModelProviders.of(this).get(RoomViewModel::class.java)
        roomViewModel.getAllWorldLiveData().observe(this, Observer<List<World>> {
            var text = ""
            it?.forEach {
                text += "${it.id}:${it.world}:${it.chineseMean}\n"
            }
            text_contact.text = text
        })
        bt_insert.setOnClickListener {
            var world1 = World(world = "hellow", chineseMean = "你好")
            var world2 = World(world = "world", chineseMean = "世界")
            roomViewModel.insertWorlds(world1)
            roomViewModel.insertWorlds(world2)
        }
        //更新一项id 为32的
        bt_updata.setOnClickListener {
            var world = World(world = "hi", chineseMean = "你好被修改")
            world.id = 140
            roomViewModel.updateWorlds(world)
        }
        //删除一项id 为35的
        bt_delect.setOnClickListener {
            var world = World(world = "hi", chineseMean = "你好被修改")
            world.id = 139
            roomViewModel.deleteWorlds(world)
        }
        bt_clear.setOnClickListener {
            roomViewModel.deleteALL()
        }
    }

}
