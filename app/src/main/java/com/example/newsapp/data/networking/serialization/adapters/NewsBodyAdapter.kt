package com.example.newsapp.data.networking.serialization.adapters

import android.net.Uri
import com.example.newsapp.data.networking.serialization.responses.NewsBody
import com.example.newsapp.domain.models.News
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class NewsBodyAdapter : JsonDeserializer<News> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext
    ): News {
        val body = context.deserialize<NewsBody>(json, NewsBody::class.java)
        return News(
            image = body?.image?.let { Uri.parse(body.image) } ?: Uri.EMPTY,
            header = body?.title.orEmpty(),
            description = body?.description.orEmpty(),
            source = body?.source?.name.orEmpty(),
            publishedAt = body?.publishedAt.orEmpty().dropLast(10),
            url = body?.url?.let { Uri.parse(body.url) } ?: Uri.EMPTY
        )
    }
}