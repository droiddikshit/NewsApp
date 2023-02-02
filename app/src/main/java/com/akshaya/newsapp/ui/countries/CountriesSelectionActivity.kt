package com.akshaya.newsapp.ui.countries

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshaya.newsapp.data.model.Country
import com.akshaya.newsapp.databinding.NewsLanguageSelectionBinding
import com.akshaya.newsapp.di.component.ActivityComponent
import com.akshaya.newsapp.ui.base.BaseActivity
import com.akshaya.newsapp.ui.countrydetails.CountryDetailsActivity
import com.akshaya.newsapp.utils.Status
import kotlinx.coroutines.launch
import javax.inject.Inject


class CountriesSelectionActivity : BaseActivity<CountrySelectionViewModel>() {

    @Inject
    lateinit var adapter: CountrySelectionAdapter

    private lateinit var binding: NewsLanguageSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewsLanguageSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
        viewModel.fetchCountries()

        initViews()
    }

    private fun initViews() {
        adapter.itemClickListener = {
            startActivity(CountryDetailsActivity.getStartIntent(this, it))
        }
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
                viewModel.sourceDetailsList.collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            it.data?.let { countryList ->
                                renderList(countryList) }
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
                                this@CountriesSelectionActivity,
                                it.message,
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(countryList: List<Country>) {
        adapter.addData(countryList)
        adapter.notifyDataSetChanged()

    }



    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }
}