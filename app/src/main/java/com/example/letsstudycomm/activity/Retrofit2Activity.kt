package com.example.letsstudycomm.activity

import android.os.Bundle
import com.example.letsstudycomm.R
import com.example.letsstudycomm.base.BaseActivity

class Retrofit2Activity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.activity_retrofit
    override fun getTitleName(): String = getString(R.string.app_bar_title_retrofit2)
}