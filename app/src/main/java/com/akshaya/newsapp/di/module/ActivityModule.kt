package com.akshaya.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.akshaya.newsapp.data.api.NetworkStatusHelper
import com.akshaya.newsapp.data.repository.TopHeadlineRepository
import com.akshaya.newsapp.di.ActivityContext
import com.akshaya.newsapp.ui.base.BaseActivity
import com.akshaya.newsapp.ui.base.ViewModelProviderFactory
import com.akshaya.newsapp.ui.countries.CountrySelectionAdapter
import com.akshaya.newsapp.ui.countries.CountrySelectionViewModel
import com.akshaya.newsapp.ui.countrydetails.CountryDetailsAdapter
import com.akshaya.newsapp.ui.countrydetails.CountryDetailsViewModel
import com.akshaya.newsapp.ui.homescreen.HomeScreenViewModel
import com.akshaya.newsapp.ui.newssource.NewsSourceAdapter
import com.akshaya.newsapp.ui.newssource.NewsSourceViewModel
import com.akshaya.newsapp.ui.newssource.sourcedetails.NewsSourceDetailsAdapter
import com.akshaya.newsapp.ui.newssource.sourcedetails.NewsSourceDetailsViewModel
import com.akshaya.newsapp.ui.searchnews.SearchAdapter
import com.akshaya.newsapp.ui.searchnews.SearchViewModel
import com.akshaya.newsapp.ui.topheadlines.TopHeadlineAdapter
import com.akshaya.newsapp.ui.topheadlines.TopHeadlineViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: BaseActivity<*>) {


    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideNewsListViewModel(
        topHeadlineRepository: TopHeadlineRepository, networkStatusHelper: NetworkStatusHelper,
    ): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository,networkStatusHelper)
            })[TopHeadlineViewModel::class.java]
    }

    @Provides
    fun provideNewsSourceViewModel(topHeadlineRepository: TopHeadlineRepository): NewsSourceViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(NewsSourceViewModel::class) {
                NewsSourceViewModel(topHeadlineRepository)
            })[NewsSourceViewModel::class.java]
    }

    @Provides
    fun provideHomeViewModel(topHeadlineRepository: TopHeadlineRepository): HomeScreenViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(HomeScreenViewModel::class) {
                HomeScreenViewModel(topHeadlineRepository)
            })[HomeScreenViewModel::class.java]
    }

    @Provides
    fun provideSrcDetailsViewModel(topHeadlineRepository: TopHeadlineRepository): NewsSourceDetailsViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(NewsSourceDetailsViewModel::class) {
                NewsSourceDetailsViewModel(topHeadlineRepository)
            })[NewsSourceDetailsViewModel::class.java]
    }

    @Provides
    fun provideLanguageSelectionViewModel(topHeadlineRepository: TopHeadlineRepository): CountrySelectionViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(CountrySelectionViewModel::class) {
                CountrySelectionViewModel(topHeadlineRepository)
            })[CountrySelectionViewModel::class.java]
    }

    @Provides
    fun provideCountryDetailsViewModel(topHeadlineRepository: TopHeadlineRepository): CountryDetailsViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(CountryDetailsViewModel::class) {
                CountryDetailsViewModel(topHeadlineRepository)
            })[CountryDetailsViewModel::class.java]
    }

    @Provides
    fun provideSearchViewModel(topHeadlineRepository: TopHeadlineRepository): SearchViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(SearchViewModel::class) {
                SearchViewModel(topHeadlineRepository)
            })[SearchViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

    @Provides
    fun provideNewsSourceAdapter() = NewsSourceAdapter(ArrayList())

    @Provides
    fun provideNewsSourceDetailsAdapter() = NewsSourceDetailsAdapter(ArrayList())

    @Provides
    fun provideCountrySelectionAdapter() = CountrySelectionAdapter(ArrayList())

    @Provides
    fun provideCountryDetailsAdapter() = CountryDetailsAdapter(ArrayList())

    @Provides
    fun provideSearchAdapter() = SearchAdapter(ArrayList())

}