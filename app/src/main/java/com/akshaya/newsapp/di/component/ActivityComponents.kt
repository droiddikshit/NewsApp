package com.akshaya.newsapp.di.component

import com.akshaya.newsapp.di.ActivityScope
import com.akshaya.newsapp.di.module.ActivityModule
import com.akshaya.newsapp.ui.countries.CountriesSelectionActivity
import com.akshaya.newsapp.ui.countrydetails.CountryDetailsActivity
import com.akshaya.newsapp.ui.homescreen.HomeScreenActivity
import com.akshaya.newsapp.ui.newssource.NewsSourceActivity
import com.akshaya.newsapp.ui.newssource.sourcedetails.NewsSourceDetailsActivity
import com.akshaya.newsapp.ui.searchnews.SearchActivity
import com.akshaya.newsapp.ui.topheadlines.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)
    fun inject(activity: HomeScreenActivity)
    fun inject(activity: NewsSourceActivity)
    fun inject(activity: NewsSourceDetailsActivity)
    fun inject(activity: CountriesSelectionActivity)
    fun inject(activity: CountryDetailsActivity)
    fun inject(activity: SearchActivity)

}