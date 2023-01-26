package com.akshaya.newsapp.data.local.db.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "news_entity")
data class NewsDao(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Long, @ColumnInfo(name = "name")
    @NotNull
    val name: String
)