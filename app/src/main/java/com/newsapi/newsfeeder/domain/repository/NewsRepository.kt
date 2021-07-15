package com.newsapi.newsfeeder.domain.repository

import com.newsapi.newsfeeder.data.model.APIResponse
import com.newsapi.newsfeeder.data.model.Article
import com.newsapi.newsfeeder.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse>
    suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse>

    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>

}