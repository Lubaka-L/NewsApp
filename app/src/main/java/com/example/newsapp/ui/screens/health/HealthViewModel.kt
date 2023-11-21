package com.example.newsapp.ui.screens.health

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.App
import com.example.newsapp.core.ResultWrapperUI
import com.example.newsapp.domain.enums.Categories
import com.example.newsapp.core.toResultWrapperUI
import com.example.newsapp.domain.models.News
import com.example.newsapp.domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HealthViewModel : ViewModel() {

    @Inject
    lateinit var repository: Repository


    private val _healthNews: MutableStateFlow<ResultWrapperUI<List<News>>> = MutableStateFlow(
        ResultWrapperUI.Loading
    )
    val healthNews = _healthNews.asStateFlow()

    init {
        App.component.inject(this)
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            _healthNews.update {
                repository.getNews(Categories.Health).toResultWrapperUI()
            }
        }
    }

}