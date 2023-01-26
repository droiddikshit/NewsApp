package com.akshaya.newsapp.data.remote

import com.akshaya.newsapp.BuildConfig
import com.akshaya.newsapp.data.api.NetworkService
import com.akshaya.newsapp.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Networking {
    const val HEADER_API_KEY = AppConstants.HEADER_API_KEY

    fun createNetworkingConfig( baseUrl: String): NetworkService {
        val client = OkHttpClient().newBuilder().addInterceptor { chain ->
            val request: Request = chain.request()
                .newBuilder()
                .addHeader(HEADER_API_KEY, BuildConfig.API_KEY)
                .build()
            chain.proceed(request)
        }.build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }
}