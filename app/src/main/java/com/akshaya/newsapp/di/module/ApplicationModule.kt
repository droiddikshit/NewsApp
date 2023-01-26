package com.akshaya.newsapp.di.module

import android.content.Context
import com.akshaya.newsapp.BuildConfig
import com.akshaya.newsapp.NewsApplication
import com.akshaya.newsapp.data.api.NetworkService
import com.akshaya.newsapp.data.remote.Networking
import com.akshaya.newsapp.di.ApplicationContext
import com.akshaya.newsapp.di.BaseUrl
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
    ): NetworkService = Networking.createNetworkingConfig(
        BuildConfig.BASE_URL,
    )
}