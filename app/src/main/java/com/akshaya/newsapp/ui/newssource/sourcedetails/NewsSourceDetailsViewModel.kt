package com.akshaya.newsapp.ui.newssource.sourcedetails

import androidx.lifecycle.viewModelScope
import com.akshaya.newsapp.data.model.Article
import com.akshaya.newsapp.data.repository.TopHeadlineRepository
import com.akshaya.newsapp.ui.base.BaseViewModel
import com.akshaya.newsapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsSourceDetailsViewModel(private val topHeadlineRepository: TopHeadlineRepository) :
    BaseViewModel() {

    private val _sourceDetailsList = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val sourceDetailsList: StateFlow<Resource<List<Article>>> = _sourceDetailsList

    fun fetchSourceNews(sourceID: String) {
        viewModelScope.launch {
            topHeadlineRepository.getNewsSourceData(sourceID)
                .catch { e ->
                    _sourceDetailsList.value = Resource.error(e.toString())
                }
                .collect {
                    _sourceDetailsList.value = Resource.success(it)
                }
        }
    }

}