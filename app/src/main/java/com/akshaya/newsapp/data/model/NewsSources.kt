package com.akshaya.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class NewsSources(
    @SerializedName("sources")
    val source : List<Source> = ArrayList(),
    @SerializedName("status")
    val status:String
)
