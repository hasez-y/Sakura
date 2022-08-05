package com.ssslzgn.common.base.net

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseViewModel: ViewModel() {

    /**
     * 统一处理错误
     * @param e 异常
     * @param showErrorToast 是否显示错误吐司
     */
    @SuppressLint("WrongConstant")
    private fun onError(e: Exception, showErrorToast: Boolean) {
        when (e) {

            // 网络请求失败
            is ConnectException, is SocketTimeoutException, is UnknownHostException -> {

            }
            // 数据解析错误

            // 其他错误
            else -> {

            }

        }
    }



}