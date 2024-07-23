package com.carousell.carousellnews.domain.services

import com.carousell.carousellnews.domain.models.NewsDataModel
import retrofit2.Call
import retrofit2.http.GET

interface GoogleApiServices {
    @GET("carousell_news.json")
    fun getData(): Call<List<NewsDataModel>>
}