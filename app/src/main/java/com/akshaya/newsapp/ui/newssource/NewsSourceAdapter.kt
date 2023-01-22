package com.akshaya.newsapp.ui.newssource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshaya.newsapp.data.model.Source
import com.akshaya.newsapp.databinding.ActivityNewsSourceItemBinding
import com.akshaya.newsapp.utils.ItemClickListener

class NewsSourceAdapter(
    private val newsSourceList: ArrayList<Source>
) : RecyclerView.Adapter<NewsSourceAdapter.DataViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<Source>
    class DataViewHolder(private val binding: ActivityNewsSourceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(source: Source, itemClickListener:ItemClickListener<Source>) {
            binding.newsSourceName.text = source.name
            binding.newsSourceName.setOnClickListener {
                itemClickListener(source)
            }
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

    override fun getItemCount(): Int = newsSourceList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(newsSourceList[position],itemClickListener)

    fun addData(list: List<Source>) {
        newsSourceList.addAll(list)
    }

}