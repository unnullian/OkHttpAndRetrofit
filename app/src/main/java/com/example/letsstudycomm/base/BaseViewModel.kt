package com.example.letsstudycomm.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

open class BaseViewModel : ViewModel() {
    val kindOfEnumActivity: MutableLiveData<KClass<*>> = MutableLiveData()
    val lvLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val lvResponseText: MutableLiveData<String> = MutableLiveData()
}