package com.akshaya.newsapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akshaya.newsapp.NewsApplication
import com.akshaya.newsapp.di.component.ActivityComponent
import com.akshaya.newsapp.di.component.DaggerActivityComponent
import com.akshaya.newsapp.di.module.ActivityModule
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)

    }

    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)


}