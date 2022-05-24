package com.example.letsstudycomm.viewModel

import com.example.letsstudycomm.base.BaseViewModel
import kotlin.reflect.KClass

class MainViewModel : BaseViewModel() {
    fun onClickButton(kClass: KClass<*>) {
        kindOfEnumActivity.value = kClass
    }
}