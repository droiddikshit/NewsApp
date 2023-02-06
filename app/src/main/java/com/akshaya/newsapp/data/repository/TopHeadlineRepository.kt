package com.akshaya.newsapp.data.repository

import com.akshaya.newsapp.data.api.NetworkService
import com.akshaya.newsapp.data.local.DatabaseHelperImpl
import com.akshaya.newsapp.data.local.entity.ArticleEntity
import com.akshaya.newsapp.data.local.json.JsonService
import com.akshaya.newsapp.data.model.Article
import com.akshaya.newsapp.data.model.Country
import com.akshaya.newsapp.data.model.NewsSources
import com.akshaya.newsapp.data.model.toArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService,private val jsonService: JsonService, private val databaseHelper: DatabaseHelperImpl) {

    fun getTopHeadlines(country: String): Flow<List<ArticleEntity>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            val articlesList = it.articles
            val articles = mutableListOf<ArticleEntity>()
            for (apiArticle in articlesList) {
                articles.add(apiArticle.toArticle())
            }
            return@map articles
        }.flatMapConcat { articles ->
            return@flatMapConcat flow {
                emit(databaseHelper.deleteAll())
            }.flatMapConcat {
                return@flatMapConcat databaseHelper.insertNewsData(articles)
            }.flatMapConcat {
                return@flatMapConcat databaseHelper.getTopHeadline()
            }
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

    fun getLanguageData(sourceId: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(sourceId))
        }.map {
            it.articles
        }
    }

    fun getCountries(): Flow<List<Country>> {
        return flow {
            emit(jsonService.getCountries())
        }
    }

}