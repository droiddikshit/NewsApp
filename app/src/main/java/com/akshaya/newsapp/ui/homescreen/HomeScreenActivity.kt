package com.akshaya.newsapp.ui.homescreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akshaya.newsapp.NewsApplication
import com.akshaya.newsapp.databinding.HomescreenuiBinding
import com.akshaya.newsapp.di.component.DaggerActivityComponent
import com.akshaya.newsapp.di.module.ActivityModule
import com.akshaya.newsapp.ui.topheadlines.TopHeadlineActivity

class HomeScreenActivity: AppCompatActivity() {

    private lateinit var binding:HomescreenuiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = HomescreenuiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        binding.topHeadlines.setOnClickListener { startActivity(Intent(this,TopHeadlineActivity::class.java)) }
    }

    private fun injectDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this@HomeScreenActivity)
    }



}