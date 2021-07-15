package com.newsapi.newsfeeder.data.db

import androidx.room.*
import com.newsapi.newsfeeder.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM article_table")
    fun getAllArticles(): Flow<List<Article>>

    @Delete
    suspend fun deleteSavedArticle(article: Article)

}