package com.example.myjeptdemo.utils

import android.view.Gravity
import android.widget.Toast
import com.example.myjeptdemo.MyApplication

/**
 * Toast 工具类
 * 耗时操作前执行toast展示
 * 原因是7.1.1系统对TYPE_TOAST的Window类型做了超时限制，绑定了Window Token，最长超时时间是3.5s，如果UI在这段时间内没有执行完，
 * Toast.show()内部的handler message得不到执行，NotificationManageService那端会把这个Toast取消掉，同时把Toast对于的window token置为无效
 */


object ToastUtil {

    fun toast(resId: Int) {
        toast(null, resId, Toast.LENGTH_SHORT, false)
    }

    fun toast(text: String) {
        toast(text, -1, Toast.LENGTH_SHORT, false)
    }

    fun toastCenter(text: String) {
        toast(text, -1, Toast.LENGTH_SHORT, true)
    }

    fun toastCenter(resId: Int) {
        toast(null, resId, Toast.LENGTH_SHORT, true)
    }


    /**
     * 线程安全的toast
     *
     * @param text
     * @param resId
     * @param duration
     * @param isCenter
     */
    private fun toast(text: String?, resId: Int, duration: Int, isCenter: Boolean) {
        val toast = Toast.makeText(
            MyApplication.getInstance().applicationContext, text
                ?: UIUtil.getString(resId), duration)
        if (isCenter) {
            toast.setGravity(Gravity.CENTER, 0, 0)
        }
        if (UIUtil.isRunMainThread) {
            toast.show()
        } else {
            UIUtil.postMainThread({ toast.show() }, this)
        }
    }
}
