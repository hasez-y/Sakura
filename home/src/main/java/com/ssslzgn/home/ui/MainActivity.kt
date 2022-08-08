package com.ssslzgn.home.ui

import android.os.Bundle
import android.view.View
import com.ssslzgn.common.base.ui.BaseVmActivity
import com.ssslzgn.home.R
import com.ssslzgn.home.databinding.ActivityMainBinding
import com.ssslzgn.home.net.view_model.HomeMainViewModel

/**
 * 主页home
 */
class MainActivity : BaseVmActivity<ActivityMainBinding, HomeMainViewModel>() {

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
        super.setListener(view)
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun viewModelClass(): Class<HomeMainViewModel> {
        return HomeMainViewModel::class.java
    }

}