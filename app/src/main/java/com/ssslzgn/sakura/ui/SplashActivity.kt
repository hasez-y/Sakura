package com.ssslzgn.sakura.ui


import android.view.View
import com.ssslzgn.common.base.ui.BaseVmActivity
import com.ssslzgn.sakura.databinding.AppActivitySplashBinding
import com.ssslzgn.sakura.net.SplashViewModel

/**
 * 启动页
 * 2s后进入首页
 */
class SplashActivity : BaseVmActivity<AppActivitySplashBinding, SplashViewModel>() {

    override fun initView() {
        super.initView()
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


}