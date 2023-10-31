package com.gokhancsml.mvvmnews.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gokhancsml.mvvmnews.models.Article
import androidx.room.*


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
     fun getAllArticles(): LiveData<List<Article>>

    @Delete
     fun deleteArticle(article: Article)
}