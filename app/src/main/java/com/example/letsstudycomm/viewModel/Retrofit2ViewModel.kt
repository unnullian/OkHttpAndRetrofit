package com.example.letsstudycomm.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.letsstudycomm.base.BaseViewModel

class Retrofit2ViewModel : BaseViewModel() {
    val lvStartRetrofit: MutableLiveData<Boolean> = MutableLiveData()
    val lvStartRetrofitWithOkhttp: MutableLiveData<Boolean> = MutableLiveData()

    fun onClickRunButtonRetrofit() {
        lvStartRetrofit.value = true
    }
    fun onClickRunButtonRetrofitWIthOkhttp() {
        lvStartRetrofitWithOkhttp.value = true
    }

}