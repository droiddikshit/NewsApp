package com.akshaya.newsapp.di.component

import com.akshaya.newsapp.di.FragmentScope
import com.akshaya.newsapp.di.module.FragmentModule
import com.akshaya.newsapp.ui.countrydetails.CountryNewsDetailsFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {
    fun inject(fragment: CountryNewsDetailsFragment)
}