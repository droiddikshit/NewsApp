package com.akshaya.newsapp.ui.countries

import androidx.lifecycle.viewModelScope
import com.akshaya.newsapp.data.model.Article
import com.akshaya.newsapp.data.model.Country
import com.akshaya.newsapp.data.repository.TopHeadlineRepository
import com.akshaya.newsapp.ui.base.BaseViewModel
import com.akshaya.newsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CountrySelectionViewModel(private val topHeadlineRepository: TopHeadlineRepository) :
    BaseViewModel() {

    private val _sourceDetailsList = MutableStateFlow<Resource<List<Country>>>(Resource.loading())

    val sourceDetailsList: StateFlow<Resource<List<Country>>> = _sourceDetailsList

    fun fetchCountries(){
        viewModelScope.launch {
            topHeadlineRepository.getCountries()
                .flowOn(Dispatchers.IO)
                .catch {
                    // handle error
                }
                .collect {
                    _sourceDetailsList.value = Resource.success(it)
                }
        }
    }
}