package com.example.letsstudycomm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity : AppCompatActivity() {
    protected abstract fun getLayoutId() : Int
    protected abstract fun getTitleName() : String
    protected lateinit var binding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutId())
        supportActionBar?.title = getTitleName()

        setContentView(binding.root)
    }
}