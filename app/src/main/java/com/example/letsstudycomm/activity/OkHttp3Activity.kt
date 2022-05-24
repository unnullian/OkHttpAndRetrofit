package com.example.letsstudycomm.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.letsstudycomm.R
import com.example.letsstudycomm.base.BaseActivity
import com.example.letsstudycomm.databinding.ActivityOkhttpBinding
import com.example.letsstudycomm.viewModel.OkHttp3ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okio.IOException

class OkHttp3Activity : BaseActivity() {

    private val okhttpViewModel: OkHttp3ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // viewModel initialize
        okhttpViewModel.apply{
            lvStartSyncRun.observe(this@OkHttp3Activity) {
                if (it) {
                    CoroutineScope(Dispatchers.IO).launch {
                        synchronousRun()
                    }
                }
            }
            lvStartAsyncRun.observe(this@OkHttp3Activity) {
                if (it) {
                    CoroutineScope(Dispatchers.IO).launch {
                        asynchronousRun()
                    }
                }
            }

        }

        (binding as ActivityOkhttpBinding).apply {
            this.viewModel = okhttpViewModel
            lifecycleOwner = this@OkHttp3Activity
        }


    }

    private fun synchronousRun() {
        val request = Request.Builder()
            .url("https://627c63fdbf2deb7174d9d193.mockapi.io/api/v1/blogs")
            .build()

        okhttpViewModel.lvLoading.postValue(true)
        OkHttpClient().newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            okhttpViewModel.lvResponseText.postValue(response.body!!.string())
            okhttpViewModel.lvLoading.postValue(false)
        }

        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(this@OkHttp3Activity, "통신 후, 동기적으로 토스트", Toast.LENGTH_LONG).show()
        }

    }

    private fun asynchronousRun() {
        val request = Request.Builder()
            .url("https://627c63fdbf2deb7174d9d193.mockapi.io/api/v1/blogs")
            .build()

        okhttpViewModel.lvLoading.postValue(true)
        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    okhttpViewModel.lvResponseText.postValue(response.body!!.string())
                    okhttpViewModel.lvLoading.postValue(false)
                }
            }
        })

        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(this@OkHttp3Activity, "통신 후, 비동기적으로 토스트", Toast.LENGTH_LONG).show()
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_okhttp
    override fun getTitleName(): String = getString(R.string.app_bar_title_okhttp3)
}