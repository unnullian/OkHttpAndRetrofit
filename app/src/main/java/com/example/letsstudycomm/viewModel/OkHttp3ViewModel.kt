package com.example.letsstudycomm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.letsstudycomm.base.BaseViewModel

class OkHttp3ViewModel : BaseViewModel() {

    val lvStartSyncRun: MutableLiveData<Boolean> = MutableLiveData()
    val lvStartAsyncRun: MutableLiveData<Boolean> = MutableLiveData()
    val lvResponseText: MutableLiveData<String> = MutableLiveData()

    fun onClickRunButtonSync() {
        lvStartSyncRun.value = true
    }
    fun onClickRunButtonAsync() {
        lvStartAsyncRun.value = true
    }
}