package com.jiutong.base.http

/**
 * Created by xiangyun_liu on 2017/9/5.
 *
 * 网络请求客户端
 *
 */

object ApiClient {
    var mApiServer: ApiService

    init {
        mApiServer = initConfig()
    }

    fun initConfig(): ApiService {
        return RetrofitConfig.mRetrofit.create(ApiService::class.java)
    }
}
