package com.gokhancsml.mvvmnews.repository

import android.app.DownloadManager.Query
import com.gokhancsml.mvvmnews.api.RetrofitInstance
import com.gokhancsml.mvvmnews.db.ArticleDatabase
import com.gokhancsml.mvvmnews.models.Article

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    fun upsert(article: Article) = db.getArticleDao().upsert(article) //suspend

    fun getSavedNews() = db.getArticleDao().getAllArticles()

     fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article) //suspend


}