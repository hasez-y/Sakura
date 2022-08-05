package com.ssslzgn.common.base.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

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

    abstract fun getViewBinding(): VB

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}