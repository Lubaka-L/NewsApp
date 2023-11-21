package com.example.newsapp.data.networking.serialization.responses

import com.google.gson.annotations.SerializedName

data class NewsBody(

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("source")
    val source: Source?

) {

    data class Source(
        @SerializedName("name")
        val name: String?
    )

}