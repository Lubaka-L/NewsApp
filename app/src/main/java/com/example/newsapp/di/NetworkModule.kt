package com.example.newsapp.di

import com.example.newsapp.data.networking.api.BASE_URL
import com.example.newsapp.data.networking.api.NewsApi
import com.example.newsapp.data.networking.serialization.adapters.NewsBodyAdapter
import com.example.newsapp.domain.models.News
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@Module
class NetWorkModule {

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(News::class.java, NewsBodyAdapter())
            .setLenient()
            .create()
    }

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
    }

    @Provides
    fun provideApi(retrofit: Retrofit.Builder): NewsApi {
        return retrofit.baseUrl(BASE_URL).build().create(NewsApi::class.java)
    }


}