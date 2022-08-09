package com.ssslzgn.sakura.ui


import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.jaeger.library.StatusBarUtil
import com.ssslzgn.common.base.ui.BaseVmActivity
import com.ssslzgn.common.config.ARouterPath
import com.ssslzgn.sakura.databinding.AppActivitySplashBinding
import com.ssslzgn.sakura.net.SplashViewModel

/**
 * 启动页
 * 2s后进入首页
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseVmActivity<AppActivitySplashBinding, SplashViewModel>() {

    private val millis = 2000L

    override fun initView() {
        super.initView()
        // 启动页沉浸式
        StatusBarUtil.setLightMode(this)
        LogUtils.d("进入启动页")
        // 2s后进入首页
        goMainDelay()
    }

    override fun observer() {
        super.observer()
    }

    override fun initData() {
        super.initData()
    }

    override fun setListener(view: View?) {

    }

    override fun getViewBinding(): AppActivitySplashBinding {
        return AppActivitySplashBinding.inflate(layoutInflater)
    }

    override fun viewModelClass(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    private fun goMainDelay() {
        object : CountDownTimer(millis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // TODO 更新UI

            }

            override fun onFinish() {
                // 通过阿里路由跳转首页
                openActivity(ARouterPath.URL_MAIN_AC)
                LogUtils.d("跳转Home")
            }
        }.start()
    }

}