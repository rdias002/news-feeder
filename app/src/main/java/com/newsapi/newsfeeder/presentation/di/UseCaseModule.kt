package com.newsapi.newsfeeder.presentation.di

import com.newsapi.newsfeeder.domain.repository.NewsRepository
import com.newsapi.newsfeeder.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsHeadlinesUseCase(repository: NewsRepository): GetNewsHeadlinesUseCase {
        return GetNewsHeadlinesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedNewsUseCase(repository: NewsRepository): GetSearchedNewsUseCase {
        return GetSearchedNewsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSaveNewsUseCase(repository: NewsRepository): SaveNewsUseCase {
        return SaveNewsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetSaveDNewsUseCase(repository: NewsRepository): GetSavedNewsUseCase {
        return GetSavedNewsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteSavedNewsUseCase(repository: NewsRepository): DeleteSavedNewsUseCase {
        return DeleteSavedNewsUseCase(repository)
    }

}