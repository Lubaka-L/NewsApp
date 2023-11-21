package com.example.newsapp.domain.repository

import com.example.newsapp.core.ResultWrapper
import com.example.newsapp.domain.enums.Categories
import com.example.newsapp.domain.models.News

interface Repository {

    suspend fun getNews(category: Categories): ResultWrapper<List<News>>

}