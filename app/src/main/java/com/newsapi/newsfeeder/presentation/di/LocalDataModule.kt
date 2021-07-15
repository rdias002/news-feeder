package com.newsapi.newsfeeder.presentation.di

import com.newsapi.newsfeeder.data.db.ArticleDao
import com.newsapi.newsfeeder.data.respository.datasource.NewsLocalDataSource
import com.newsapi.newsfeeder.data.respository.datasourceimpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideNewsLocalDataSource(articleDao: ArticleDao): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(articleDao)
    }
}