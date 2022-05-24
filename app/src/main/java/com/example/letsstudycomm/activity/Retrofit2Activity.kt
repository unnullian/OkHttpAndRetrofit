package com.example.letsstudycomm.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.letsstudycomm.Constants
import com.example.letsstudycomm.R
import com.example.letsstudycomm.api.mock_interface.MockService
import com.example.letsstudycomm.api.model.MockApiModel
import com.example.letsstudycomm.base.BaseActivity
import com.example.letsstudycomm.databinding.ActivityRetrofitBinding
import com.example.letsstudycomm.viewModel.Retrofit2ViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Retrofit2Activity : BaseActivity() {

    private val retrofitViewModel: Retrofit2ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (binding as ActivityRetrofitBinding).apply {
            this.viewModel = retrofitViewModel
        }

        retrofitViewModel.lvStartRetrofit.observe(this) {
            if (it) {
                runRetrofit()
            }
        }

        retrofitViewModel.lvStartRetrofitWithOkhttp.observe(this) {
            if (it) {
                runRetrofitWithOkhttp()
            }
        }
    }

    private fun runRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val mockService = retrofit.create(MockService::class.java)
        mockService.getMockData().enqueue(object : Callback<List<MockApiModel>> {
            override fun onResponse(
                call: Call<List<MockApiModel>>,
                response: Response<List<MockApiModel>>
            ) {
                if (!response.isSuccessful) throw IOException()
                retrofitViewModel.lvResponseText.value = response.body().toString()
            }

            override fun onFailure(call: Call<List<MockApiModel>>, t: Throwable) {
                Toast.makeText(applicationContext, "통신 실패", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun runRetrofitWithOkhttp() {

        // 기본적인 로깅 인터셉터
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        // 커스텀 로깅 인터셉터
        val printInterceptor = HttpLoggingInterceptor {
            retrofitViewModel.lvResponseText.postValue(it)
        }.apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(printInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val mockService = retrofit.create(MockService::class.java)
        mockService.getMockData().enqueue(object : Callback<List<MockApiModel>> {
            override fun onResponse(
                call: Call<List<MockApiModel>>,
                response: Response<List<MockApiModel>>
            ) {
                Toast.makeText(applicationContext, "통신 성공", Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: Call<List<MockApiModel>>, t: Throwable) {
                Toast.makeText(applicationContext, "통신 실패", Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun getLayoutId(): Int = R.layout.activity_retrofit
    override fun getTitleName(): String = getString(R.string.app_bar_title_retrofit2)
}