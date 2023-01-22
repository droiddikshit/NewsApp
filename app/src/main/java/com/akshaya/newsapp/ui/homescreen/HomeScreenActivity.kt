package com.akshaya.newsapp.ui.homescreen

import android.content.Intent
import android.os.Bundle
import com.akshaya.newsapp.databinding.HomescreenuiBinding
import com.akshaya.newsapp.di.component.ActivityComponent
import com.akshaya.newsapp.ui.base.BaseActivity
import com.akshaya.newsapp.ui.newssource.NewsSourceActivity
import com.akshaya.newsapp.ui.topheadlines.TopHeadlineActivity

class HomeScreenActivity: BaseActivity() {

    private lateinit var binding:HomescreenuiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomescreenuiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        binding.topHeadlines.setOnClickListener { startActivity(Intent(this,TopHeadlineActivity::class.java)) }
        binding.newsSource.setOnClickListener { startActivity(Intent(this,NewsSourceActivity::class.java)) }
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }


}