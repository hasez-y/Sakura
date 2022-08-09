package com.ssslzgn.sakura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ssslzgn.common.config.ARouterPath
import com.ssslzgn.sakura.R

@Route(path = ARouterPath.URL_MAIN_AC)
class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }
}