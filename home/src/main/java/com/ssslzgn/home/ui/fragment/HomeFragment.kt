package com.ssslzgn.home.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssslzgn.common.base.ui.BaseVMFragment
import com.ssslzgn.home.R
import com.ssslzgn.home.adapter.HomeBannerAdapter
import com.ssslzgn.home.databinding.ActivityMainBinding
import com.ssslzgn.home.databinding.FragmentHomeBinding
import com.ssslzgn.home.gson.BannerData
import com.ssslzgn.home.net.view_model.HomeFragmentViewModel
import com.youth.banner.indicator.CircleIndicator


class HomeFragment(context: Context) :
    BaseVMFragment<FragmentHomeBinding, HomeFragmentViewModel>() {

    override fun initView() {
        // 初始化轮播图
        initBanner()


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

    /**
     * 初始化轮播图Banner
     */
    private fun initBanner() {
        mBinding.homeBanner.addBannerLifecycleObserver(this)//添加生命周期观察者
            .setAdapter(
                HomeBannerAdapter(
                    mutableListOf(
                        BannerData(""),
                        BannerData("")
                    )
                )
            ).indicator =
            CircleIndicator(context)
    }

    override fun getViewBinding(): FragmentHomeBinding? {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun viewModelClass(): Class<HomeFragmentViewModel> {
        return HomeFragmentViewModel::class.java
    }


}