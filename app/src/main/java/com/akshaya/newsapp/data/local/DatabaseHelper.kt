package com.akshaya.newsapp.data.local
import kotlinx.coroutines.flow.Flow
import com.akshaya.newsapp.data.local.entity.ArticleEntity

interface DatabaseHelper {
    fun getTopHeadline(): Flow<List<ArticleEntity>>

    fun insertNewsData(article: List<ArticleEntity>): Flow<Unit>

    fun deleteAll(): Int

}