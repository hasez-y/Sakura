package com.ssslzgn.sakura.ui


import android.os.CountDownTimer
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.ssslzgn.common.base.ui.BaseVmActivity
import com.ssslzgn.sakura.databinding.AppActivitySplashBinding
import com.ssslzgn.sakura.net.SplashViewModel

/**
 * 启动页
 * 2s后进入首页
 */
class SplashActivity : BaseVmActivity<AppActivitySplashBinding, SplashViewModel>() {

    private val millis = 200L

    override fun initView() {
        super.initView()
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
        object : CountDownTimer(millis, 100) {
            override fun onTick(millisUntilFinished: Long) {
                // TODO 更新UI

            }

            override fun onFinish() {
                // TODO 通过阿里路由跳转首页

            }
        }
    }

}