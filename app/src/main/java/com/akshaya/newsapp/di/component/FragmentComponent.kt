package com.akshaya.newsapp.di.component

import com.akshaya.newsapp.di.FragmentScope
import com.akshaya.newsapp.di.module.FragmentModule
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {
}