package com.newsapi.newsfeeder.data.respository.datasourceimpl

import com.newsapi.newsfeeder.data.db.ArticleDao
import com.newsapi.newsfeeder.data.model.Article
import com.newsapi.newsfeeder.data.respository.datasource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articleDao: ArticleDao
) : NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        articleDao.insert(article)
    }

    override suspend fun deletedArticle(article: Article) {
        articleDao.deleteSavedArticle(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles()
    }
}