package com.example.myjeptdemo.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.myjeptdemo.*


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2019/12/23
 * @Description:
 */
class ViewModelList : ViewModel() {
    var context: Context? = null
    fun jumpMainActivity() {
        context?.apply {
            this.startActivity(Intent(context, MainActivity::class.java))
        }

    }

    fun jumpVMSavectivity() {
        context?.apply {
            this.startActivity(Intent(context, ViewModelSaveActivity::class.java))
        }

    }

    fun jumpViewModelActivityActivity() {
        context?.apply {
            this.startActivity(Intent(context, ViewModelActivityActivity::class.java))
        }

    }

    fun jumpViewRoomOneActivity() {
        context?.apply {
            this.startActivity(Intent(context, RoomOneActivity::class.java))
        }

    }

    fun jumpViewRoomLiveDataActivity() {
        context?.apply {
            this.startActivity(Intent(context, RoomTwoActivity::class.java))
        }

    }

    fun jumpRoomViewModelActivity() {
        context?.apply {
            this.startActivity(Intent(context, RoomViewModelActivity::class.java))
        }

    }
    fun jumpNeiHanActivity() {
        context?.apply {
            this.startActivity(Intent(context, NeiHanActivity::class.java))
        }

    }
}