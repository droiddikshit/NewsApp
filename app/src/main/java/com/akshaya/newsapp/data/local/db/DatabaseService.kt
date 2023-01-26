package com.akshaya.newsapp.data.local.db

import androidx.room.RoomDatabase
import com.akshaya.newsapp.data.local.db.dao.NewsDao

abstract class DatabaseService : RoomDatabase() {

    abstract fun NewsListDao(): NewsDao
}