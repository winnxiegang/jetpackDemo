//package com.jiutong.base.http
//
//import android.content.Context
//import com.jiutong.base.componet.IBaseView
//import com.jiutong.base.entity.BaseResult
//import com.jiutong.base.utils.*
//import com.jiutong.haofahuo.BuildConfig
//import com.jiutong.haofahuo.R
//import com.jiutong.haofahuo.constant.ResponseCodeConstant
//import com.jiutong.haofahuo.entity.requestEntity.JLogEntity
//import com.jiutong.haofahuo.util.UserFileUtil
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.observers.DisposableObserver
//import retrofit2.HttpException
//
//
///**
// * BaseObserver
// *
// * 接口开始请求时调用onHttpStart方法，
// * 请求成功（code正确）调用onHttpSuccess方法，
// * 请求失败（HttpException或者code 错误）时调用onHttpError方法（当code返回失败时不解析data,直接抛出HttpErrorException异常，详见 {@link com.jiutong.base.http.GsonResponseBodyConverter}）
// * 请求结束调用onHttpEnd 方法
// *
// *
// * @author: xiangyun_liu
// *
// * @date: 2018/8/20 14:29
// */
//abstract class BaseObserver<T : BaseResult>(private val iBaseView: IBaseView? = null) : DisposableObserver<T>() {
//    private var showLoad: Boolean = true
//    private var showMyLoadMsg: String = "正在加载..."
//    private var cancelable: Boolean = false
//    private var type: Int = 1
//
//    /**
//     * 默认 展示等待框  可实现取消 可实现重写展示msg
//     */
//    constructor(iBaseView: IBaseView? = null, showLoad: Boolean = false, cancelable: Boolean = false, showMyLoadMsg: String = "正在加载...", compositeDisposable: CompositeDisposable) : this(iBaseView) {
//        this.showLoad = showLoad
//        this.showMyLoadMsg = showMyLoadMsg
//        this.cancelable = cancelable
//        compositeDisposable.add(this)
//        type = 3
//    }
//
//    /**
//     * 默认 展示等待框  可实现取消
//     */
//    constructor(iBaseView: IBaseView? = null, showLoad: Boolean = false, cancelable: Boolean = false, compositeDisposable: CompositeDisposable) : this(iBaseView) {
//        this.showLoad = showLoad
//        this.cancelable = cancelable
//        compositeDisposable.add(this)
//        type = 2
//    }
//
//    /**
//     * 默认 展示等待框 不可取消
//     */
//    constructor(iBaseView: IBaseView? = null, showLoad: Boolean = false, compositeDisposable: CompositeDisposable) : this(iBaseView) {
//        this.showLoad = showLoad
//        compositeDisposable.add(this)
//        type = 1
//    }
//
//    /**
//     * 请求开始
//     *
//     * 发起请求的时候调用，该方法一定会调用
//     */
//    open fun onHttpStart() {
//        showLoad?.apply {
//            if (showLoad) {
//                when (type) {
//                    1 -> iBaseView?.showLoading("正在加载...")
//                    2 -> iBaseView?.showLoading("正在加载...", cancelable)
//                    3 -> iBaseView?.showLoading(showMyLoadMsg, cancelable)
//                }
//            }
//        }
//
//    }
//
//    /**
//     * 请求成功
//     *
//     * http请求成功，并且服务端code返回正确的时候调用
//     */
//    abstract fun onHttpSuccess(t: T)
//
//    /**
//     * http失败或者code 错误的时候调用
//     *
//     * 默认打印Toast 错误信息
//     */
//    open fun onHttpError(e: HttpErrorException) {
//        if (showLoad) iBaseView?.dismissLoading()
//    }
//
//    /**
//     * 请求结束
//     *
//     * 请求结束的时候调用，无论请求结果如何，该方法一定会调用
//     */
//    open fun onHttpEnd() {
//        if (showLoad) iBaseView?.dismissLoading()
//    }
//
//    /**
//     * 订阅
//     */
//    override fun onStart() {
//        super.onStart()
//
//        try {
//            onHttpStart()
//        } catch (e: Exception) {
//            showCodeException(e)
//        }
//    }
//
//    /**
//     * 成功
//     */
//    override fun onNext(t: T) {
//        if (t.isSuccess()) {
//            try {
//                onHttpSuccess(t)
//            } catch (e: Exception) {
//                showCodeException(e)
//            }
//        }
//        try {
//            onHttpEnd()
//        } catch (e: Exception) {
//            showCodeException(e)
//        }
//    }
//
//    /**
//     * 请求失败
//     * HttpException 时处理HttpException异常
//     * HttpErrorException 时处理code 错误
//     * 其他exception 不处理
//     */
//    override fun onError(e: Throwable) {
//        try {
//            e.printStackTrace()
//
//            //处理Http错误，如果是Http错误我们把Throwable向下转型成HttpException就可以获取Response信息
//            if (e is HttpException) {
//                try {
//                    handleExceptionCode(e)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//
//                //处理HttpErrorException异常
//            } else if (e is HttpErrorException) {
//                ToastUtil.toast(e.message)
//                onHttpError(e)
//
//
//                //处理代码exception
//            } else {
//                showCodeException(e)
//
//                onHttpError(HttpErrorException(HttpErrorException.ERROR_SYSTEM, if (e.message != null) e.message!! else e.toString()))
//            }
//        } catch (e: Exception) {
//            showCodeException(e)
//        }
//
//        try {
//            onHttpEnd()
//        } catch (e: Exception) {
//            showCodeException(e)
//        }
//    }
//
//    /**
//     * 结束
//     */
//    override fun onComplete() {
//        try {
//            onHttpEnd()
//        } catch (e: Exception) {
//            showCodeException(e)
//        }
//    }
//
//    /**
//     * 单独处理http异常（非 200） 错误
//     */
//    private fun handleExceptionCode(exception: HttpException) {
//        LogUtils.d(exception.toString())
//        val code = exception.code()
//        val responseBody = exception.response().errorBody()
//        if (responseBody != null) {
//            val baseResult = GsonUtil.mGson.fromJson(responseBody.string(), BaseResult::class.java)
//            if (code == 401) {
//                val isExit = exitCodeHandle(baseResult.code)
//                if (isExit) {
//                    ToastUtil.toast(R.string.other_device_login)
//                } else {
//                    ToastUtil.toast(baseResult.msg)
//                }
//            } else if (code == -1) {
//                ToastUtil.toast(baseResult.msg)
//            }
//            onHttpError(HttpErrorException(baseResult.code, baseResult.msg))
//
//        } else {
//
//            val msg = UIUtil.getString(R.string.system_error)
//            ToastUtil.toast(msg)
//            onHttpError(HttpErrorException(HttpErrorException.ERROR_SYSTEM, msg))
//        }
//    }
//
//
//    /**
//     * 退出code处理
//     */
//    private fun exitCodeHandle(code: Int): Boolean {
//        when (code) {
//            //用户被封禁，无效的token,回到登录界面
//            ResponseCodeConstant.ERROR_BANNED_CODE,
//            ResponseCodeConstant.ERROR_NO_LOGIN_IN,
//            ResponseCodeConstant.ERROR_LACK_TOKEN_CODE,
//            ResponseCodeConstant.ERROR_INVALID_TOKEN_CODE -> {
//                UserFileUtil.exit(iBaseView as Context)
//                return true
//            }
//        }
//        return false
//    }
//
//    /**
//     * 显示代码导致的程序异常信息
//     */
//    private fun showCodeException(e: Throwable) {
//        e.printStackTrace()
//        if (BuildConfig.DEBUG) {
//            try {
//                iBaseView?.apply {
//                    DialogUtil.showTipsDialog(iBaseView as Context, text = if (e.message != null) e.message!! else e.toString())
//                }
//            } catch (e: Exception) {
//            }
//        } else {
//            ToastUtil.toast(R.string.system_error)
//
//
//            //代码异常上传日志
//            val timestamp = System.currentTimeMillis()
//            val entity = JLogEntity(content = OtherUtil.getErrorInfoFromException(e), ip = "0.0.0.0", lever = 0, local = "what")
//            ApiClient.mApiServer.uploadLog(timestamp, HttpRequestUtil.generateSign(timestamp, entity),
//                    HttpRequestUtil.convertRequestBody(entity))
//                    .compose(RxTransformer.io2Main())
//                    .subscribe({}, {})
//        }
//    }
//}