package com.example.myjeptdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.myjeptdemo.databinding.ActivityViewModelListBinding
import com.example.myjeptdemo.viewmodel.MyViewModel
import com.example.myjeptdemo.viewmodel.ViewModelList

class ViewModelListActivity : AppCompatActivity() {
    private lateinit var dataBing: ActivityViewModelListBinding
    private lateinit var myViewModel: ViewModelList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBing = DataBindingUtil.setContentView(this, R.layout.activity_view_model_list)
        myViewModel = ViewModelProviders.of(this).get(ViewModelList::class.java)
        myViewModel.context = this
        dataBing.data = myViewModel
        dataBing.lifecycleOwner = this
    }
}
