package com.akshaya.newsapp.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshaya.newsapp.databinding.ActivityNewsSourceItemBinding

class CountrySelectionAdapter(
    private val languageData: ArrayList<HashMap<String?, String?>>
) : RecyclerView.Adapter<CountrySelectionAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ActivityNewsSourceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(holder: ArrayList<HashMap<String?, String?>>) {
            binding.languageNames.text = holder.get(position).get("name")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ActivityNewsSourceItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = languageData.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(languageData)
}