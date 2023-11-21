package com.example.newsapp

import android.app.Application
import com.example.newsapp.di.AppComponent
import com.example.newsapp.di.AppModule
import com.example.newsapp.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
    }

}