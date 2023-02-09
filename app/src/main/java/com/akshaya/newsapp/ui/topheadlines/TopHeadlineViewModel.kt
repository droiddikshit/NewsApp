package com.akshaya.newsapp.ui.topheadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshaya.newsapp.data.api.NetworkStatusHelper
import com.akshaya.newsapp.data.local.entity.ArticleEntity
import com.akshaya.newsapp.data.model.Article
import com.akshaya.newsapp.data.repository.TopHeadlineRepository
import com.akshaya.newsapp.ui.base.BaseViewModel
import com.akshaya.newsapp.utils.AppConstants.COUNTRY
import com.akshaya.newsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class TopHeadlineViewModel(private val topHeadlineRepository: TopHeadlineRepository, private val networkStatusHelper: NetworkStatusHelper,
) : BaseViewModel() {

    private val _articleList = MutableStateFlow<Resource<List<ArticleEntity>>>(Resource.loading())

    val articleList: StateFlow<Resource<List<ArticleEntity>>> = _articleList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch(Dispatchers.Main) {
            topHeadlineRepository.getTopHeadlines(COUNTRY)
                .flowOn(Dispatchers.IO)

                .catch { e ->
                    _articleList.value = Resource.error(e.toString())
                }
                .collect {
                    _articleList.value = Resource.success(it)
                }
        }
    }

}