package com.example.newsapp.data

import com.google.gson.annotations.SerializedName

data class ListWrapper<T>(
    @SerializedName("articles")
    val articles: T
)