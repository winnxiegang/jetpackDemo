package com.jiutong.base.http



/**
 * Created by xiangyun_liu on 2017/9/4.
 *
 *
 * 网络请求接口管理
 *
 *
 *
 * 接口请求定义：
 * http://wiki.9tong.com/pages/viewpage.action?pageId=1409143
 *问题记录
 * java.lang.IllegalArgumentException: No Retrofit annotation found. (parameter #4)
解决：看看自己的参数有没有声明类型。我的是忘了加@Field.
Post 请求 目前后台一直使用实体类进行传参
 */

interface ApiService {
    companion object {
        /**
         * 接口地址
         */
        val API_ADDRESS = ""
    }

    /**
     * 获取短信验证码，或者校验图形验证码
     */
   // @POST("message/sms/send")
    //fun getSMSCode(@Header(TIME_STAMP) timestamp: Long, @Header(SIGN) sign: String, @Body jCheckGraphCodeEntity: RequestBody): Observable<BaseResult>

}
