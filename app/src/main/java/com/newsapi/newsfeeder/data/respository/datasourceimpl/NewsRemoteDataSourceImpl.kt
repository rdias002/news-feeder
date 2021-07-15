package com.newsapi.newsfeeder.data.respository.datasourceimpl

import com.newsapi.newsfeeder.data.api.NewsAPIService
import com.newsapi.newsfeeder.data.model.APIResponse
import com.newsapi.newsfeeder.data.respository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse> {
        return newsAPIService.getTopHeadlines(country, page)
    }

    override suspend fun searchNewsHeadlines(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<APIResponse> {
        return newsAPIService.searchNewsHeadlines(country, searchQuery, page)
    }

}