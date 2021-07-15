package com.newsapi.newsfeeder.presentation.di

import com.newsapi.newsfeeder.data.respository.NewsRepositoryImpl
import com.newsapi.newsfeeder.data.respository.datasource.NewsLocalDataSource
import com.newsapi.newsfeeder.data.respository.datasource.NewsRemoteDataSource
import com.newsapi.newsfeeder.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource, newsLocalDataSource)
    }
}