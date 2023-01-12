package com.akshaya.newsapp.di.component

import com.akshaya.newsapp.di.ActivityScope
import com.akshaya.newsapp.di.module.ActivityModule
import com.akshaya.newsapp.ui.topheadlines.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)

}