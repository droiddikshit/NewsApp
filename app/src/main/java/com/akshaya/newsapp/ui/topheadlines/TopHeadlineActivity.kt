package com.akshaya.newsapp.ui.topheadlines

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshaya.newsapp.data.model.Article
import com.akshaya.newsapp.databinding.ActivityTopHeadlineBinding
import com.akshaya.newsapp.di.component.ActivityComponent
import com.akshaya.newsapp.ui.base.BaseActivity
import com.akshaya.newsapp.utils.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopHeadlineActivity: BaseActivity<TopHeadlineViewModel>() {

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    private lateinit var binding: ActivityTopHeadlineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopHeadlineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.articleList.collect {
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
                            Toast.makeText(this@TopHeadlineActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
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
