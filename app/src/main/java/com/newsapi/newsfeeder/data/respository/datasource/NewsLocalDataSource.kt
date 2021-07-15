package com.newsapi.newsfeeder.data.respository.datasource

import com.newsapi.newsfeeder.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article: Article)
    suspend fun deletedArticle(article: Article)
    fun getSavedArticles(): Flow<List<Article>>

}