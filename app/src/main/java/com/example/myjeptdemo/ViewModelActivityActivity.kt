package com.example.myjeptdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ViewModelActivityActivity : AppCompatActivity() {
    companion object {
        const val dataKey = "DATA_KEY"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_activity)
    }
}
