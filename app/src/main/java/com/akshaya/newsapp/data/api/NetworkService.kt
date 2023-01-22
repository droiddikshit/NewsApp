package com.akshaya.newsapp.data.api

import com.akshaya.newsapp.data.model.NewsSources
import com.akshaya.newsapp.data.model.Source
import com.akshaya.newsapp.data.model.SourceDetailsResponse
import com.akshaya.newsapp.data.model.TopHeadlinesResponse
import com.akshaya.newsapp.utils.AppConstants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines/sources")
    suspend fun getNewsSource(): NewsSources

    @Headers("X-Api-Key: $API_KEY")
    @GET("everything")
    suspend fun getNewsSourceData(@Query("sources") sourceId: String): SourceDetailsResponse

}