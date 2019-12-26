package com.example.myjeptdemo.utils

import android.app.Activity
import android.content.Context

/**
 * @author: xiangyun_liu
 *
 * @date: 2018/8/24 15:31
 */
object UserFileUtil {
    /**
     * 所有账户共用的文件
     */
    private val PUBLIC_FILE = "public_file"
    /**
     * 当前登录用户的token
     */
    private const val TOKEN = "token"
    /**
     * 设备ID
     */
    private const val DEVICE_ID = "device_id"
    /**
     * 上次登录的手机号
     */
    private const val LOGIN_PHONE = "login_phone"
    /**
     * 登录用户名
     */
    private const val LOGIN_USER_NAME = "login_user_name"
    /**
     * 登录用户的身份
     */
    private const val LOGIN_USER_ROLES = "login_user_roles"

    /**
     * 读取当前登录用户的token
     */
    fun readToken(context: Context): String {
        val prefs = context.getSharedPreferences(PUBLIC_FILE, Activity.MODE_PRIVATE)
        return prefs.getString(TOKEN, "")!!
    }

    /**
     * 保存当前登录用户的token
     */
    fun saveToken(context: Context, token: String) {
        val prefs = context.getSharedPreferences(PUBLIC_FILE, Activity.MODE_PRIVATE)
        prefs.edit().putString(TOKEN, token).commit()
    }

    /**
     * 清除当前登录用户的token
     */
    fun clearToken(context: Context) {
        saveToken(context, "")
    }

    /**
     * 读取device id
     */
    fun readDeviceId(context: Context): String? {
        val prefs = context.getSharedPreferences(PUBLIC_FILE, Activity.MODE_PRIVATE)
        return prefs.getString(DEVICE_ID, "")
    }

    /**
     * 保存device id
     */
    fun saveDeviceId(context: Context, deviceId: String) {
        val prefs = context.getSharedPreferences(PUBLIC_FILE, Activity.MODE_PRIVATE)
        prefs.edit().putString(DEVICE_ID, deviceId).commit()
    }


}