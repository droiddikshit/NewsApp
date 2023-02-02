package com.akshaya.newsapp.data.local

import com.akshaya.newsapp.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

class DatabaseHelperImpl : DatabaseHelper {
    override fun getTopHeadline(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}