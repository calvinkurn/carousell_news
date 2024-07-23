package com.carousell.carousellnews.domain.repository

import com.carousell.carousellnews.domain.models.NewsDataModel
import com.carousell.carousellnews.domain.services.GoogleApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GoogleApiRepository {
    private const val API_URL = "https://storage.googleapis.com/carousell-interview-assets/android/"

    suspend fun fetchData(
        onError: (error: Throwable) -> Unit = {},
        onSuccess: (data: List<NewsDataModel>?) -> Unit
    ) {
        val retrofit = Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val apiService = retrofit.create(GoogleApiServices::class.java)
        apiService.getData().enqueue(object : Callback<List<NewsDataModel>>{
            override fun onFailure(call: Call<List<NewsDataModel>>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<List<NewsDataModel>>, response: Response<List<NewsDataModel>>) {
                onSuccess(response.body())
            }
        })
    }
}

