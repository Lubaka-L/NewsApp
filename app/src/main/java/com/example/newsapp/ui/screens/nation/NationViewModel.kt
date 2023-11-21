package com.example.newsapp.ui.screens.nation

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

class NationViewModel : ViewModel() {

    @Inject
    lateinit var repository: Repository

    private val _nationNews: MutableStateFlow<ResultWrapperUI<List<News>>> = MutableStateFlow(
        ResultWrapperUI.Loading
    )
    val nationNews = _nationNews.asStateFlow()

    init {
        App.component.inject(this)
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            _nationNews.update {
                repository.getNews(Categories.Nation).toResultWrapperUI()
            }
        }
    }

}