package com.newsapi.newsfeeder.domain.usecase

import com.newsapi.newsfeeder.data.model.APIResponse
import com.newsapi.newsfeeder.data.util.Resource
import com.newsapi.newsfeeder.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val repository: NewsRepository) {
    //This function does not necessarily always have to directly return from repository.
    //We could also include business logic to transform and process the data before
    //returning it. All business logic can be placed here
    suspend fun execute(country: String, page: Int): Resource<APIResponse> =
        repository.getNewsHeadlines(country, page)
}