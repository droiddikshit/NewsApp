package com.akshaya.newsapp.ui.searchnews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshaya.newsapp.data.model.Article
import com.akshaya.newsapp.databinding.SearchUiBinding
import com.akshaya.newsapp.di.component.ActivityComponent
import com.akshaya.newsapp.ui.base.BaseActivity
import com.akshaya.newsapp.utils.getQueryTextChangeStateFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchActivity : BaseActivity<SearchViewModel>() {

    companion object {
        fun getStartSearchIntent(context: Context) = Intent(context, SearchActivity::class.java)
    }

    @Inject
    lateinit var adapter: SearchAdapter

    private lateinit var binding: SearchUiBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchUiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
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

        val searchView = binding.searchView
        lifecycleScope.launch {
            searchView.getQueryTextChangeStateFlow()
                .debounce(600)
                .filter { query ->
                    val validQuery = query.isNotEmpty() && query.length > 3
                    if (!validQuery) {
                        binding.progressBar.visibility = View.GONE
                        renderList(emptyList())
                        binding.recyclerView.visibility = View.VISIBLE

                    } else {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    return@filter validQuery

                }
                .distinctUntilChanged()
                .flowOn(Dispatchers.Main)
                .flatMapLatest { query ->
                    return@flatMapLatest viewModel.fetchSearchNews(query)
                        .catch {
                            emitAll(flowOf(emptyList()))
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    binding.progressBar.visibility = View.GONE
                    result.let { newsList -> renderList(newsList) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
        }
    }


    private fun renderList(apiArticleList: List<Article>) {
        adapter.replaceData(apiArticleList)
        adapter.notifyDataSetChanged()
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

}
