package com.akshaya.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("language")
    val language: String = "",
    @SerializedName("country")
    val country: String = "",
)