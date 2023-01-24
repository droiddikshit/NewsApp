package com.akshaya.newsapp.ui.newssource.sourcedetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshaya.newsapp.data.model.Article
import com.akshaya.newsapp.databinding.ActivityNewsSourceBinding
import com.akshaya.newsapp.di.component.ActivityComponent
import com.akshaya.newsapp.ui.base.BaseActivity
import com.akshaya.newsapp.utils.AppConstants.NEWS_SRC_KEY
import com.akshaya.newsapp.utils.Status
import javax.inject.Inject

class NewsSourceDetailsActivity : BaseActivity() {

    @Inject
    lateinit var newsSourceViewModel: NewsSourceDetailsViewModel

    @Inject
    lateinit var adapter: NewsSourceDetailsAdapter

    private lateinit var binding: ActivityNewsSourceBinding
    private var newsSourceId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsSourceBinding.inflate(layoutInflater)
        newsSourceId = getIntent().getStringExtra(NEWS_SRC_KEY).toString()
        setContentView(binding.root)
        setupUI()
        setupObserver()
        newsSourceViewModel.fetchSourceNews(newsSourceId)
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launchWhenStarted {
            newsSourceViewModel.sourceDetailsList.collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        it.data?.let { newsList -> renderList(newsList) }
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        //Handle Error
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this@NewsSourceDetailsActivity,
                            it.message,
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }

            }

        }

    }

    private fun renderList(articleList: List<Article>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }
}