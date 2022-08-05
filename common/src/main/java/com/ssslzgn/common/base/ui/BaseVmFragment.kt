package com.ssslzgn.common.base.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ssslzgn.common.base.net.BaseViewModel

abstract class BaseVMFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFragment<VB>() {
    protected lateinit var mViewModel: VM
    private var lazyLoaded = false

    //分页参数
    protected open val mTotalCount = 20 //每次加载数量
    protected open var mCurrentSize = 0 //当前加载数量
    protected open var mCurrentPage = 0 //当前加载页数

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        observe()
        initView()
        initData()
        setListener()
    }

    open fun setListener() {
    }

    open fun initData() {
    }

    open fun initView() {
    }

    open fun observe() {
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    abstract fun viewModelClass(): Class<VM>

    override fun onResume() {
        super.onResume()
        if(!lazyLoaded){
            lazyLoadData()
            lazyLoaded=true
        }

    }

    open fun lazyLoadData() {
    }
}
