package com.akshaya.newsapp.data.local

import com.akshaya.newsapp.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseHelperImpl @Inject constructor(private val appDatabase: AppDataBase) :
    DatabaseHelper {

    override fun getTopHeadline(): Flow<List<ArticleEntity>> =
        appDatabase.articleDao().getAllTopHeadline()

    override fun insertNewsData(article: List<ArticleEntity>): Flow<Unit> = flow {
        appDatabase.articleDao().insertAll(article)
        emit(Unit)
    }

    override fun deleteAll(): Int = appDatabase.articleDao().deleteAll()


}