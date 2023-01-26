package com.akshaya.newsapp.data.api

import com.akshaya.newsapp.data.model.NewsSources
import com.akshaya.newsapp.data.model.SourceDetailsResponse
import com.akshaya.newsapp.data.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String
    ): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getNewsSource(
    ): NewsSources

    @GET("everything")
    suspend fun getNewsSourceData(
        @Query("sources") sourceId: String
    ): SourceDetailsResponse

}