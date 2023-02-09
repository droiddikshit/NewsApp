package com.akshaya.newsapp.ui.homescreen

import android.content.Intent
import android.os.Bundle
import com.akshaya.newsapp.databinding.HomescreenuiBinding
import com.akshaya.newsapp.di.component.ActivityComponent
import com.akshaya.newsapp.ui.base.BaseActivity
import com.akshaya.newsapp.ui.countries.CountriesSelectionActivity
import com.akshaya.newsapp.ui.newssource.NewsSourceActivity
import com.akshaya.newsapp.ui.searchnews.SearchActivity
import com.akshaya.newsapp.ui.topheadlines.TopHeadlineActivity

class HomeScreenActivity : BaseActivity<HomeScreenViewModel>() {

    private lateinit var binding: HomescreenuiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomescreenuiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        binding.topHeadlines.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    TopHeadlineActivity::class.java
                )
            )
        }
        binding.newsSource.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    NewsSourceActivity::class.java
                )
            )
        }
        binding.coroutines.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CountriesSelectionActivity::class.java
                )
            )
        }

        binding.search.setOnClickListener {
            startActivity(SearchActivity.getStartSearchIntent(this))
        }
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }


}