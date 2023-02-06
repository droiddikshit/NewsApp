package com.akshaya.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akshaya.newsapp.data.local.db.dao.NewsDao
import com.akshaya.newsapp.data.local.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun articleDao(): NewsDao

}