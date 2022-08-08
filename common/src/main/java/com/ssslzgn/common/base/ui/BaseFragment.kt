package com.ssslzgn.common.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected open var binding: VB? = null
    protected open val mBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return mBinding.root
    }

    /**
     * 阿里路由跳转
     */
    fun openActivity(path: String) {
        ARouter.getInstance().build(path).navigation()
    }

    abstract fun getViewBinding(): VB?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    open fun initialize() {
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
