package com.example.myjeptdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import androidx.lifecycle.ViewModelProviders
import com.example.myjeptdemo.databinding.ActivityMainBinding
import com.example.myjeptdemo.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var myViewModel: MyViewModel
    private lateinit var databing: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databing = DataBindingUtil.setContentView(this, R.layout.activity_main)
        myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        databing.data = myViewModel
        databing.lifecycleOwner = this
    }
}
