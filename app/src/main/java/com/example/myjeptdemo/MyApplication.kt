package com.example.myjeptdemo

import android.app.Application
import android.os.Handler
import android.os.Message
import android.view.WindowManager
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader


/**
 *
 * HaoFaHuoApplicationLike这个类是Application的代理类，以前所有在Application的实现必须要全部拷贝到这里
 *
 *
 * @author: xiangyun_liu
 *
 * @date: 2019/4/23 18:20
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        //application初始化
        onOldCreate()
    }




    /********************************   以前的Application初始化操作    ********************************/
    companion object {
        /**
         * Logger日志全局Tag
         */
        private lateinit var mMApplication: Application
        private var mMainThreadId: Long = 0
        private lateinit var mMainThreadHandler: HandlerProxy
        /**
         * 获取Application Context
         */
        fun getInstance() =
            mMApplication


        /**
         * 获取主线程id
         */
        fun getMainThreadId() =
            mMainThreadId

        /**
         * 获取主线程handler
         */
        fun getMainThreadHandler() =
            mMainThreadHandler

        //静态代码块
        init {
            initSmartRefreshLayout()
        }

        /**
         * 设置SmartRefreshLayout  header footer样式
         * 此种设置方式优先级最低
         */
        private fun initSmartRefreshLayout() {
            //设置全局的Header构建器
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { _, layout ->
                layout.setPrimaryColorsId(
                    R.color.colorAccent,
                    R.color.colorAccent) //全局设置主题颜色
                ClassicsHeader(mMApplication) //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
            //设置全局的Footer构建器
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { _, layout ->
                //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter(mMApplication).setDrawableSize(20f)
            }
        }
    }

    //fix Toast$TN.handleShow
    class HandlerProxy(private val mHandler: Handler) : Handler() {

        override fun handleMessage(msg: Message) {
            try {
                mHandler.handleMessage(msg)
            } catch (e: WindowManager.BadTokenException) {
                //ignore
            }

        }
    }

    private fun onOldCreate() {
        mMApplication = this
        mMainThreadId = Thread.currentThread().id
        mMainThreadHandler = HandlerProxy(Handler())
    }

}