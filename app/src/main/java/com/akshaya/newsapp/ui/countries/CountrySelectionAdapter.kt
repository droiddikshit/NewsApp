package com.akshaya.newsapp.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshaya.newsapp.databinding.ActivityNewsSourceItemBinding
import com.akshaya.newsapp.databinding.LanguageSelectionItemBinding
import com.akshaya.newsapp.utils.ItemClickListener

class CountrySelectionAdapter(
    private val languageData: ArrayList<HashMap<String?, String?>>
) : RecyclerView.Adapter<CountrySelectionAdapter.DataViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<String>

    class DataViewHolder(private val binding: LanguageSelectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(holder: ArrayList<HashMap<String?, String?>>,itemClickListener: ItemClickListener<String>) {
            binding.languageNames.text = holder.get(position).get("name")
            binding.layoutCounrtyCode.setOnClickListener{
                itemClickListener(holder.get(position).get("name").toString())
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
        holder.bind(languageData,itemClickListener)

}