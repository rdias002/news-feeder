package com.newsapi.newsfeeder.data.respository.datasource

import com.newsapi.newsfeeder.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse>
    suspend fun searchNewsHeadlines(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<APIResponse>
}