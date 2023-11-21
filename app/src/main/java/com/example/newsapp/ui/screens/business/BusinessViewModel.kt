package com.example.newsapp.ui.screens.business

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

class BusinessViewModel : ViewModel() {

    @Inject
    lateinit var repository: Repository

    private val _businessNews: MutableStateFlow<ResultWrapperUI<List<News>>> = MutableStateFlow(
        ResultWrapperUI.Loading
    )
    val businessNews = _businessNews.asStateFlow()

    init {
        App.component.inject(this)
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            _businessNews.update {
                repository.getNews(Categories.Business).toResultWrapperUI()
            }
        }
    }
}