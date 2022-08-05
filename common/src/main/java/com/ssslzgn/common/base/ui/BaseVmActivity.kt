package com.ssslzgn.common.base.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ssslzgn.common.base.net.BaseViewModel

abstract class BaseVmActivity<VB : ViewBinding, VM : BaseViewModel> : BaseActivity<VB>(), View.OnClickListener {
    protected open lateinit var mViewModel: VM

    //加载数量
    protected open val mTotalCount = 20 //每次加载数量
    protected open var mCurrentSize = 0 //当前加载数量
    protected open var mCurrentPage = 0 //当前加载页数

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        observer()
        initView()
        initData()
        setListener()
    }

    /**
     * 初始化UI界面
     */
    open fun initView() {
    }

    /**
     * 初始化观察者
     */
    open fun observer() {
    }

    /**
     * 请求数据
     */
    open fun initData() {
    }

    /**
     * 设置监听
     */
    open fun setListener(view: View? = null) {
    }

    /**
     * 点击时间处理
     */
    override fun onClick(p0: View?) {
        setListener(p0)
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    abstract fun viewModelClass(): Class<VM>

    override fun onDestroy() {
        super.onDestroy()
        mCurrentSize = 0
        mCurrentPage = 0
    }
}

