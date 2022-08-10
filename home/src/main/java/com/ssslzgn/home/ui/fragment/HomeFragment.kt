package com.ssslzgn.home.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssslzgn.common.base.ui.BaseVMFragment
import com.ssslzgn.home.R
import com.ssslzgn.home.adapter.HomeBannerAdapter
import com.ssslzgn.home.databinding.ActivityMainBinding
import com.ssslzgn.home.databinding.FragmentHomeBinding
import com.ssslzgn.home.gson.BannerData
import com.ssslzgn.home.net.view_model.HomeFragmentViewModel
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.transformer.AlphaPageTransformer
import com.youth.banner.transformer.DepthPageTransformer
import com.youth.banner.transformer.ScaleInTransformer
import com.youth.banner.transformer.ZoomOutPageTransformer


class HomeFragment(context: Context) :
    BaseVMFragment<FragmentHomeBinding, HomeFragmentViewModel>() {

    /**
     * 测试图片
     */
    private var testImgUrl = "https://iknow-pic.cdn.bcebos.com/622762d0f703918fbcbf079e523d269759eec473"

    override fun initView() {
        // 初始化轮播图
        initBanner()
        initHomeRec()
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
                        BannerData(testImgUrl),
                        BannerData(testImgUrl)
                    )
                )
            )
            // 设置动画效果
            .addPageTransformer(AlphaPageTransformer())
            .addPageTransformer(ScaleInTransformer())
            // 设置指示器选中颜色
            .setIndicatorSelectedColorRes(com.ssslzgn.common.R.color.skyBlue)
            .setIndicatorNormalColorRes(com.ssslzgn.common .R.color.skyBlue)

            // 设置指示器位置右边
            .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
            // 设置圆角
            .setBannerRound(10f)
            .indicator =
            CircleIndicator(context)
    }

    /**
     * 初始化主页数据列表
     */
    private fun initHomeRec() {
        mBinding.homeRec.layoutManager = LinearLayoutManager(context)
    }

    override fun getViewBinding(): FragmentHomeBinding? {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun viewModelClass(): Class<HomeFragmentViewModel> {
        return HomeFragmentViewModel::class.java
    }


}