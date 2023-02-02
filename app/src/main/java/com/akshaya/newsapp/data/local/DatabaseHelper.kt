package com.akshaya.newsapp.data.local
import kotlinx.coroutines.flow.Flow
import com.akshaya.newsapp.data.local.entity.Article

interface DatabaseHelper {
    fun getTopHeadline(): Flow<List<Article>>
}