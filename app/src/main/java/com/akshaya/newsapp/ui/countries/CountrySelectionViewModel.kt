package com.akshaya.newsapp.ui.countries

import com.akshaya.newsapp.data.model.Article
import com.akshaya.newsapp.data.repository.TopHeadlineRepository
import com.akshaya.newsapp.ui.base.BaseViewModel
import com.akshaya.newsapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CountrySelectionViewModel(private val topHeadlineRepository: TopHeadlineRepository) :
    BaseViewModel() {


    private val _sourceDetailsList = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val sourceDetailsList: StateFlow<Resource<List<Article>>> = _sourceDetailsList
}