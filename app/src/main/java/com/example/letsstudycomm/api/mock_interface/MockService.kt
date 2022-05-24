package com.example.letsstudycomm.api.mock_interface

import com.example.letsstudycomm.api.model.MockApiModel
import retrofit2.Call
import retrofit2.http.GET

interface MockService {
    @GET("/api/v1/blogs")
    fun getMockData() : Call<List<MockApiModel>>
}