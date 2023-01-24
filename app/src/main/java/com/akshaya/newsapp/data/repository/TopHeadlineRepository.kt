package com.akshaya.newsapp.data.repository

import com.akshaya.newsapp.data.api.NetworkService
import com.akshaya.newsapp.data.model.Article
import com.akshaya.newsapp.data.model.NewsSources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {

    fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }

    fun getNewsSource(): Flow<NewsSources> {
        return flow {
            emit(networkService.getNewsSource())
        }.map {
            it
        }
    }

    fun getNewsSourceData(sourceId: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsSourceData(sourceId))
        }.map {
            it.articles
        }
    }

}