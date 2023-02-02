package com.akshaya.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akshaya.newsapp.data.local.entity.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

}