package com.example.myjeptdemo.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.myjeptdemo.R
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btRegisiter.setOnClickListener {
            //声明一个跳转控制器
            val findNavController = Navigation.findNavController(it)
            findNavController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    //每次切换都要进行onCreate进行创建
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(activity, "LoginFragment", Toast.LENGTH_LONG).show()
    }
}
