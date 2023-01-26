package com.akshaya.newsapp.ui.topheadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshaya.newsapp.data.model.Article
import com.akshaya.newsapp.data.repository.TopHeadlineRepository
import com.akshaya.newsapp.ui.base.BaseViewModel
import com.akshaya.newsapp.utils.AppConstants.COUNTRY
import com.akshaya.newsapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class TopHeadlineViewModel(private val topHeadlineRepository: TopHeadlineRepository) : BaseViewModel() {

    private val _articleList = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val articleList: StateFlow<Resource<List<Article>>> = _articleList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines(COUNTRY)
                .catch { e ->
                    _articleList.value = Resource.error(e.toString())
                }
                .collect {
                    _articleList.value = Resource.success(it)
                }
        }
    }

}