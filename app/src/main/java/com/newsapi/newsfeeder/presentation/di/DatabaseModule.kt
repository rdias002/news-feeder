package com.newsapi.newsfeeder.presentation.di

import android.app.Application
import androidx.room.Room
import com.newsapi.newsfeeder.data.db.ArticleDao
import com.newsapi.newsfeeder.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(
            app,
            NewsDatabase::class.java,
            "news_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun providesNewsDao(newsDatabase: NewsDatabase): ArticleDao {
        return newsDatabase.getArticleDao()
    }
}