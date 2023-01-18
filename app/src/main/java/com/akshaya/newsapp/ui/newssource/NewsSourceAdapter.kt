package com.akshaya.newsapp.ui.newssource

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.akshaya.newsapp.data.model.Source
import com.akshaya.newsapp.databinding.ActivityNewsSourceItemBinding

class NewsSourceAdapter(
    private val articleList: ArrayList<Source>
) : RecyclerView.Adapter<NewsSourceAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: ActivityNewsSourceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsArticle: Source) {
            binding.newsSourceName.text = newsArticle.name
            binding.newsSourceName.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(
                    it.context,
                    Uri.parse("https://timesofindia.indiatimes.com/?from=mdr")
                )
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

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(articleList[position])

    fun addData(list: List<Source>) {
        articleList.addAll(list)
    }

}