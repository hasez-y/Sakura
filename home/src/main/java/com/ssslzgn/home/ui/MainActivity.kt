package com.ssslzgn.home.ui

import android.graphics.Color
import android.graphics.ColorFilter
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.SimpleColorFilter
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.google.android.material.tabs.TabLayout
import com.ssslzgn.common.base.ui.BaseVmActivity
import com.ssslzgn.common.config.ARouterPath
import com.ssslzgn.home.R
import com.ssslzgn.home.databinding.ActivityMainBinding
import com.ssslzgn.home.net.view_model.HomeMainViewModel
import com.ssslzgn.home.ui.fragment.HomeFragment

/**
 * 主页home
 */
@Route(path = ARouterPath.URL_MAIN_AC)
class MainActivity : BaseVmActivity<ActivityMainBinding, HomeMainViewModel>() {

    override fun initView() {
        LogUtils.d("进入首页")
        // 初始化底部导航
        initBottomTab()

    }

    override fun observer() {
        super.observer()
    }

    override fun initData() {
        super.initData()
    }

    override fun setListener(view: View?) {
        super.setListener(view)
        // 底部tab点击事件
        mBinding.homeTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.setSelected()
                tab?.let { mBinding.homeVP.currentItem = it.position }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.setUnselected()

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    /**
     * 选中状态
     */
    fun TabLayout.Tab.setSelected() {
        this.customView?.let {
            val textView = it.findViewById<TextView>(R.id.tv_tab_text)
            val selectedColor =
                ContextCompat.getColor(this@MainActivity, com.ssslzgn.common.R.color.skyBlue)
            textView.setTextColor(selectedColor)

            val imageView = it.findViewById<LottieAnimationView>(R.id.homeTabImg)
            if (!imageView.isAnimating) {
                imageView.playAnimation()
            }
            setLottieColor(imageView, true)
        }
    }

    /**
     * 未选中状态
     */
    fun TabLayout.Tab.setUnselected() {
        this.customView?.let {
            val textView = it.findViewById<TextView>(R.id.tv_tab_text)
            val unselectedColor =
                ContextCompat.getColor(this@MainActivity, com.ssslzgn.common.R.color.black)
            textView.setTextColor(unselectedColor)

            val imageView = it.findViewById<LottieAnimationView>(R.id.homeTabImg)
            if (imageView.isAnimating) {
                imageView.cancelAnimation()
                imageView.progress = 0f // 还原初始状态
            }
            setLottieColor(imageView, false)
        }
    }

    /**
     * 设置 lottie icon 颜色
     */
    private fun setLottieColor(imageView: LottieAnimationView?, isSelected: Boolean) {
        imageView?.let {
            val color =
                if (isSelected) com.ssslzgn.common.R.color.skyBlue else com.ssslzgn.common.R.color.black
            val csl = AppCompatResources.getColorStateList(this, color)
            val filter = SimpleColorFilter(csl.defaultColor)
            val keyPath = KeyPath("**")
            val callback = LottieValueCallback<ColorFilter>(filter)
            it.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback)
        }
    }

    /**
     * 简单ViewPager适配器
     */
    private inner class SimpleFragmentPagerAdapter constructor(
        fm: FragmentManager,
        tabTitles: MutableCollection<String>? = null
    ) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val tabTitles = arrayOf("Android", "Kotlin", "Flutter")
        private val fragment = arrayOf(HomeFragment(), HomeFragment(), HomeFragment())

        override fun getItem(position: Int): Fragment {
            return fragment[position]
        }

        override fun getCount(): Int {
            return fragment.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return tabTitles[position]
        }
    }

    /**
     * 初始化底部tab栏
     */
    private fun initBottomTab() {
        mBinding.homeTabLayout.setupWithViewPager(mBinding.homeVP)
        mBinding.homeVP.adapter = SimpleFragmentPagerAdapter(supportFragmentManager)
        val animMap = mapOf("主页" to R.raw.stars, "资源" to R.raw.stars, "我的" to R.raw.stars)
        var count = 0
        animMap.keys.forEach { s ->
            val tab = mBinding.homeTabLayout.getTabAt(count++)
            val view = LayoutInflater.from(this).inflate(R.layout.home_tab_item, null)
            val imageView = view.findViewById<LottieAnimationView>(R.id.homeTabImg)
            val textView = view.findViewById<TextView>(R.id.tv_tab_text)
            imageView.setAnimation(animMap[s]!!)
            imageView.setColorFilter(Color.BLUE)
            textView.text = s
            tab!!.customView = view
        }
        // 默认展示首页Fragment
        mBinding.homeTabLayout.getTabAt(0)?.setSelected()
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun viewModelClass(): Class<HomeMainViewModel> {
        return HomeMainViewModel::class.java
    }

}