package com.ssslzgn.home.weight

import android.content.Context
import com.lxj.xpopup.core.DrawerPopupView
import com.ssslzgn.home.R

/**
 * 主页左侧弹窗
 */
class HomeLeftPop(context: Context): DrawerPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.home_left_popup
    }

    override fun onCreate() {
        super.onCreate()

    }

}