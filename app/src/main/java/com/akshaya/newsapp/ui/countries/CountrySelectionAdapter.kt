package com.akshaya.newsapp.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshaya.newsapp.databinding.ActivityNewsSourceItemBinding
import com.akshaya.newsapp.databinding.LanguageSelectionItemBinding

class CountrySelectionAdapter(
    private val languageData: ArrayList<HashMap<String?, String?>>,
    private val countrySelectionListner: CountrySelectionListner?
) : RecyclerView.Adapter<CountrySelectionAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: LanguageSelectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(holder: ArrayList<HashMap<String?, String?>>,countrySelectionListner: CountrySelectionListner?) {
            binding.languageNames.text = holder.get(position).get("name")
            binding.layoutCounrtyCode.setOnClickListener{
            countrySelectionListner?.onCountrySelectionListner(holder.get(position).get("name").toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LanguageSelectionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = languageData.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(languageData,countrySelectionListner)

    interface CountrySelectionListner {
        fun onCountrySelectionListner(data: String)
    }
}