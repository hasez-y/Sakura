package com.ssslzgn.home.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssslzgn.common.base.ui.BaseVMFragment
import com.ssslzgn.home.R
import com.ssslzgn.home.databinding.FragmentBlankBinding
import com.ssslzgn.home.databinding.FragmentHomeBinding
import com.ssslzgn.home.net.view_model.HomeFragmentViewModel

class BlankFragment : BaseVMFragment<FragmentBlankBinding, HomeFragmentViewModel>() {

    override fun initView() {

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

    override fun getViewBinding(): FragmentBlankBinding? {
        return FragmentBlankBinding.inflate(layoutInflater)
    }

    override fun viewModelClass(): Class<HomeFragmentViewModel> {
        return HomeFragmentViewModel::class.java
    }


}