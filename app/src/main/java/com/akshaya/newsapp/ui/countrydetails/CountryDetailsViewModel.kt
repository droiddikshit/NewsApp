package com.akshaya.newsapp.ui.countrydetails

import androidx.lifecycle.viewModelScope
import com.akshaya.newsapp.data.model.Article
import com.akshaya.newsapp.data.repository.TopHeadlineRepository
import com.akshaya.newsapp.ui.base.BaseViewModel
import com.akshaya.newsapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CountryDetailsViewModel(private val topHeadlineRepository: TopHeadlineRepository) :
    BaseViewModel() {

    private val _languageDetailsList = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val languageDetailsList: StateFlow<Resource<List<Article>>> = _languageDetailsList

    fun fetchSourceNews(sourceID: String) {
        viewModelScope.launch {
            topHeadlineRepository.getLanguageData(sourceID)
                .catch { e ->
                    _languageDetailsList.value = Resource.error(e.toString())
                }
                .collect {
                    _languageDetailsList.value = Resource.success(it)
                }
        }
    }
}