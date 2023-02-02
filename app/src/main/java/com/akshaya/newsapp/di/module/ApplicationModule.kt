package com.akshaya.newsapp.di.module

import android.content.Context
import androidx.room.Room
import com.akshaya.newsapp.BuildConfig
import com.akshaya.newsapp.NewsApplication
import com.akshaya.newsapp.data.api.ApiKeyInterceptor
import com.akshaya.newsapp.data.api.NetworkService
import com.akshaya.newsapp.data.local.db.DatabaseService
import com.akshaya.newsapp.data.remote.Networking
import com.akshaya.newsapp.di.ApiKey
import com.akshaya.newsapp.di.ApplicationContext
import com.akshaya.newsapp.di.BaseUrl
import com.akshaya.newsapp.di.DatabaseName
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
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

    @ApiKey
    @Provides
    fun provideAPIKey(): String = BuildConfig.API_KEY

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(NetworkService::class.java)
    }

    @DatabaseName
    @Provides
    fun provideDatabaseName(): String = "newsdb"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @DatabaseName name: String
    ): DatabaseService = Room.databaseBuilder(
        context,
        DatabaseService::class.java,
        name
    ).build()
}