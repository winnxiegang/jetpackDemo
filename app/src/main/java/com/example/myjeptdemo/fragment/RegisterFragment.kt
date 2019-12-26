package com.example.myjeptdemo.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.myjeptdemo.R
import com.example.myjeptdemo.ViewModelListActivity
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bt_Login.setOnClickListener {
            //声明一个跳转控制器
            val findNavController = Navigation.findNavController(it)
            findNavController.navigate(R.id.action_registerFragment_to_loginFragment)
        }
        bt_Main.setOnClickListener { startActivity(Intent(activity, ViewModelListActivity::class.java)) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(activity, "RegisterFragment", Toast.LENGTH_LONG).show()
    }
}
