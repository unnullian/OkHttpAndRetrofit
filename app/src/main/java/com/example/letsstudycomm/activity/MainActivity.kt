package com.example.letsstudycomm.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.letsstudycomm.viewModel.MainViewModel
import com.example.letsstudycomm.R
import com.example.letsstudycomm.base.BaseActivity
import com.example.letsstudycomm.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (binding as ActivityMainBinding).viewModel = viewModel

        // viewModel initialize
        viewModel.kindOfEnumActivity.observe(this) { kClass ->
            val intent = Intent(this@MainActivity, kClass.java)
            startActivity(intent)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getTitleName(): String = getString(R.string.app_bar_title_main)
}