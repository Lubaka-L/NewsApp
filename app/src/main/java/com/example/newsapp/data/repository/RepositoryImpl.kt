package com.example.newsapp.data.repository

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.newsapp.R
import com.example.newsapp.core.ResponseExtension.handleResponse
import com.example.newsapp.core.ResponseExtension.returnSafely
import com.example.newsapp.core.ResultWrapper
import com.example.newsapp.domain.enums.Categories
import com.example.newsapp.data.networking.api.NewsApi
import com.example.newsapp.domain.models.News
import com.example.newsapp.domain.repository.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : Repository {

    override suspend fun getNews(category: Categories): ResultWrapper<List<News>> {
        return returnSafely {
            when (val request = newsApi.getNews(category = category.toString().replaceFirstChar { it.lowercase() }).handleResponse()) {
                is ResultWrapper.Success -> ResultWrapper.Success(request.data.articles)
                is ResultWrapper.Error -> ResultWrapper.Error(request.error)
            }
        }
    }

}