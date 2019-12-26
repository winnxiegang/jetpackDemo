package com.example.myjeptdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import com.example.myjeptdemo.databinding.ActivityViewModelSaveBinding
import com.example.myjeptdemo.viewmodel.ViewModelSave

class ViewModelSaveActivity : AppCompatActivity() {
    private lateinit var myViewModel: ViewModelSave
    private lateinit var databing: ActivityViewModelSaveBinding

    companion object {
        const val saveKey = "SAVE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databing = DataBindingUtil.setContentView(this, R.layout.activity_view_model_save)
        //SavedStateViewModelFactory(this.application, this) 保存数据应用
        myViewModel =
            ViewModelProviders.of(this, SavedStateViewModelFactory(application, this))
                .get(ViewModelSave::class.java)
        databing.data = myViewModel
        databing.lifecycleOwner = this
    }
}