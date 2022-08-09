package com.ssslzgn.common.base.ui

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.jaeger.library.StatusBarUtil
import com.ssslzgn.common.R

/**
 * BaseActivity 基类
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected open lateinit var mBinding: VB

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewBinding()
        // 隐藏标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        // 沉浸式状态栏
        StatusBarUtil.setColor(this, resources.getColor(R.color.white),0)
        // 设置字体颜色为黑色
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        setContentView(mBinding.root)
        // 竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        // 初始化ARouter
        ARouter.getInstance().inject(this)

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
//        mBinding = null
    }

}