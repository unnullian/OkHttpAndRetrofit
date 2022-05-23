package com.example.letsstudycomm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.letsstudycomm.base.BaseViewModel

class OkHttp3ViewModel : BaseViewModel() {

    val lvStartRun: MutableLiveData<Boolean> = MutableLiveData()
    val lvResponseText: MutableLiveData<String> = MutableLiveData()

    fun onClickRunButton() {
        lvStartRun.value = true
    }
}