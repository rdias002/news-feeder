package com.newsapi.newsfeeder.presentation.di

import com.newsapi.newsfeeder.data.api.NewsAPIService
import com.newsapi.newsfeeder.data.respository.datasource.NewsRemoteDataSource
import com.newsapi.newsfeeder.data.respository.datasourceimpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsAPIService: NewsAPIService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsAPIService)
    }
}