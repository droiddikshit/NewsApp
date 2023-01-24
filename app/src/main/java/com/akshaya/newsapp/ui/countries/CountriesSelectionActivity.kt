package com.akshaya.newsapp.ui.countries

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshaya.newsapp.databinding.NewsLanguageSelectionBinding
import com.akshaya.newsapp.di.component.ActivityComponent
import com.akshaya.newsapp.ui.base.BaseActivity
import com.akshaya.newsapp.utils.ReadJsonHelper.loadJSONFromAssets
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject


class CountriesSelectionActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: CountrySelectionViewModel

    @Inject
    lateinit var adapter: CountrySelectionAdapter

    private lateinit var binding: NewsLanguageSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewsLanguageSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFromJSONFile()
    }

    fun loadFromJSONFile() {
        try {
            val obj = JSONObject(loadJSONFromAssets(this))
            val status: String = obj.getString("status")
            if (status == "Success") {
                val contactArray: JSONArray = obj.getJSONArray("data")
                val countryList: ArrayList<HashMap<String?, String?>> = ArrayList()
                var countryData: HashMap<String?, String?>
                for (i in 0 until contactArray.length()) {
                    val contacts = contactArray.getJSONObject(i)
                    val name = contacts.getString("name")
                    countryData = HashMap()
                    countryData["name"] = name
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
}