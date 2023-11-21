package com.example.newsapp.di

import com.example.newsapp.data.networking.api.NewsApi
import com.example.newsapp.data.repository.RepositoryImpl
import com.example.newsapp.domain.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideRepository(newsApi: NewsApi): Repository {
        return RepositoryImpl(newsApi)
    }

}