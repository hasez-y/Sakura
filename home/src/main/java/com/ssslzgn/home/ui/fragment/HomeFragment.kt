package com.ssslzgn.home.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssslzgn.common.base.ui.BaseVMFragment
import com.ssslzgn.home.R
import com.ssslzgn.home.databinding.ActivityMainBinding
import com.ssslzgn.home.databinding.FragmentHomeBinding
import com.ssslzgn.home.net.view_model.HomeFragmentViewModel


class HomeFragment :BaseVMFragment<FragmentHomeBinding, HomeFragmentViewModel>() {


    override fun getViewBinding(): FragmentHomeBinding? {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun viewModelClass(): Class<HomeFragmentViewModel> {
        return HomeFragmentViewModel::class.java
    }


}