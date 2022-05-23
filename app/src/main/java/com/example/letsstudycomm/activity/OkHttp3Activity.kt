package com.example.letsstudycomm.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.letsstudycomm.R
import com.example.letsstudycomm.base.BaseActivity
import com.example.letsstudycomm.databinding.ActivityOkhttpBinding

class OkHttp3Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun getLayoutId(): Int = R.layout.activity_okhttp
    override fun getTitleName(): String = getString(R.string.app_bar_title_okhttp3)
}