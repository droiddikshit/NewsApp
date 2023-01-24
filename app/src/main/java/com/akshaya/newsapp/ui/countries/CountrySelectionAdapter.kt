package com.akshaya.newsapp.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshaya.newsapp.databinding.ActivityNewsSourceItemBinding
import com.akshaya.newsapp.databinding.LanguageSelectionItemBinding

class CountrySelectionAdapter(
    private val languageData: ArrayList<HashMap<String?, String?>>
) : RecyclerView.Adapter<CountrySelectionAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: LanguageSelectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(holder: ArrayList<HashMap<String?, String?>>) {
            binding.languageNames.text = holder.get(position).get("name")
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
        holder.bind(languageData)
}