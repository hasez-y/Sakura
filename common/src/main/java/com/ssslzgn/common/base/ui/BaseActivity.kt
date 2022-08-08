package com.ssslzgn.common.base.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter

/**
 * BaseActivity 基类
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected open var mBinding: VB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewBinding()
        setContentView(mBinding?.root)
        // 竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    /**
     * 阿里路由跳转
     */
    fun openActivity(path: String) {
        ARouter.getInstance().build(path).navigation()
    }

    abstract fun getViewBinding(): VB

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}