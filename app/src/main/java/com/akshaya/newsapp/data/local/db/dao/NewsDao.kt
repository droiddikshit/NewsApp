package com.akshaya.newsapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.akshaya.newsapp.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM ArticleEntity")
    fun getAllTopHeadline(): Flow<List<ArticleEntity>>

    @Insert
    fun insertAll(topHeadline: List<ArticleEntity>)

    @Query("Delete from ArticleEntity")
    fun deleteAll(): Int

}