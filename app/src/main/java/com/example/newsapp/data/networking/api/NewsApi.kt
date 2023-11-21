package com.example.newsapp.data.networking.api

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.ListWrapper
import com.example.newsapp.domain.models.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://gnews.io"

interface NewsApi {

    @GET("/api/v4/top-headlines")
    suspend fun getNews(
        @Query("apikey") apikey: String = BuildConfig.API_KEY,
        @Query("country") country: String = "ru",
        @Query("category") category: String
    ): Response<ListWrapper<List<News>>>

}