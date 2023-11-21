package com.example.newsapp.ui.screens.entertainment

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

class EntertainmentViewModel : ViewModel() {

    @Inject
    lateinit var repository: Repository

    private val _entertainmentNews: MutableStateFlow<ResultWrapperUI<List<News>>> =
        MutableStateFlow(
            ResultWrapperUI.Loading
        )
    val entertainmentNews = _entertainmentNews.asStateFlow()

    init {
        App.component.inject(this)
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            _entertainmentNews.update {
                repository.getNews(Categories.Entertainment).toResultWrapperUI()
            }
        }
    }

}