package com.akshaya.newsapp.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshaya.newsapp.data.repository.TopHeadlineRepository
import com.akshaya.newsapp.ui.base.BaseFragment
import com.akshaya.newsapp.ui.base.ViewModelProviderFactory
import com.akshaya.newsapp.ui.countrydetails.FragmentViewModel
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: BaseFragment) {
    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideHomeLanguageSelectionViewModel(topHeadlineRepository: TopHeadlineRepository): FragmentViewModel {
        return ViewModelProvider(fragment,
            ViewModelProviderFactory(FragmentViewModel::class) {
                FragmentViewModel(topHeadlineRepository)
            })[FragmentViewModel::class.java]
    }
}