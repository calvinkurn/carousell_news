package com.carousell.carousellnews.domain.models

import com.google.gson.annotations.SerializedName

data class NewsDataModel (
    val id: String,
    val title: String,
    val description: String,
    @SerializedName("banner_url")
    val bannerUrl: String,
    @SerializedName("time_created")
    val timeCreated: Long,
    val rank: String
)