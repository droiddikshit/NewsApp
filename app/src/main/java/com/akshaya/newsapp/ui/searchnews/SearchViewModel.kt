package com.akshaya.newsapp.ui.searchnews

import com.akshaya.newsapp.data.repository.TopHeadlineRepository
import com.akshaya.newsapp.ui.base.BaseViewModel

class SearchViewModel(private val topHeadlineRepository: TopHeadlineRepository) :
    BaseViewModel(){
    fun fetchSearchNews(query: String) = topHeadlineRepository.getSearchNews(query)

}