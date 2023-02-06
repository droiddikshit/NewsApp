package com.akshaya.newsapp.data.model

import com.akshaya.newsapp.data.local.entity.ArticleEntity
import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val imageUrl: String = "",
    @SerializedName("source")
    val source: Source
)

fun Article.toArticle(): ArticleEntity = ArticleEntity(
    title = this.title,
    description = this.description,
    url = this.url,
    imageUrl = this.imageUrl,
    sourceId = this.source.id,
    sourceName = this.source.name
)
