package com.newsapi.newsfeeder.domain.usecase

import com.newsapi.newsfeeder.data.model.Article
import com.newsapi.newsfeeder.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val repository: NewsRepository) {
    //This function does not necessarily always have to directly return from repository.
    //We could also include business logic to transform and process the data before
    //returning it. All business logic can be placed here
    fun execute(): Flow<List<Article>> = repository.getSavedNews()
}