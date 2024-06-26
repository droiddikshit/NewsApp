package com.akshaya.newsapp.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ApiKey

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class DatabaseName