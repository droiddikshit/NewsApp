package com.akshaya.newsapp.di.component

import android.content.Context
import com.akshaya.newsapp.NewsApplication
import com.akshaya.newsapp.data.api.NetworkService
import com.akshaya.newsapp.data.repository.TopHeadlineRepository
import com.akshaya.newsapp.di.ApplicationContext
import com.akshaya.newsapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository

}