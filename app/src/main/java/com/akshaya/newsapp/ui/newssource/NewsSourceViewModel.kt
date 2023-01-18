package com.akshaya.newsapp.ui.newssource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshaya.newsapp.data.model.NewsSources
import com.akshaya.newsapp.data.repository.TopHeadlineRepository
import com.akshaya.newsapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsSourceViewModel (private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {

    private val _articleList = MutableStateFlow<Resource<NewsSources>>(Resource.loading())

    val articleList: StateFlow<Resource<NewsSources>> = _articleList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            topHeadlineRepository.getNewsSource()
                .catch { e ->
                    _articleList.value = Resource.error(e.toString())
                }
                .collect {
                    _articleList.value = Resource.success(it)
                }
        }
    }
}