package com.akshaya.newsapp.ui.countries

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshaya.newsapp.databinding.NewsLanguageSelectionBinding
import com.akshaya.newsapp.di.component.ActivityComponent
import com.akshaya.newsapp.ui.base.BaseActivity
import com.akshaya.newsapp.ui.countrydetails.CountryDetailsActivity
import com.akshaya.newsapp.utils.AppConstants
import com.akshaya.newsapp.utils.ReadJsonHelper.loadJSONFromAssets
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject


class CountriesSelectionActivity : BaseActivity<CountrySelectionViewModel>() {

    @Inject
    lateinit var adapter: CountrySelectionAdapter

    private lateinit var binding: NewsLanguageSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewsLanguageSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFromJSONFile()
        initViews()
    }

    private fun initViews() {
        adapter.itemClickListener = {
            startActivity(CountryDetailsActivity.getStartIntent(this, it))
        }
    }

    fun loadFromJSONFile() {
        try {
            val obj = JSONObject(loadJSONFromAssets(this))
            val status: String = obj.getString(AppConstants.STATUS)
            if (status == AppConstants.SUCCESS) {
                val contactArray: JSONArray = obj.getJSONArray("data")
                val countryList: ArrayList<HashMap<String?, String?>> = ArrayList()
                var countryData: HashMap<String?, String?>
                for (i in 0 until contactArray.length()) {
                    val contacts = contactArray.getJSONObject(i)
                    val name = contacts.getString(AppConstants.NAME)
                    countryData = HashMap()
                    countryData[AppConstants.NAME] = name
                    countryList.add(countryData)
                }
                val contactAdapter = CountrySelectionAdapter(countryList)
                binding.recyclerView.setLayoutManager(LinearLayoutManager(this))
                binding.recyclerView.setAdapter(contactAdapter)
                binding.progressBar.visibility = View.GONE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

//    override fun onCountrySelectionListner(data: String) {
//        startActivity(Intent(this,CountryDetailsActivity::class.java).putExtra(
//            AppConstants.LANGUAGE_CODE_KEY,
//            data
//        ))
//    }
}