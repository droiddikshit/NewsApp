package com.akshaya.newsapp.ui.searchnews

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.akshaya.newsapp.data.model.Article
import com.akshaya.newsapp.databinding.TopHeadlineItemLayoutBinding
import com.bumptech.glide.Glide

class SearchAdapter(
    private val apiArticleList: ArrayList<Article>
) : RecyclerView.Adapter<SearchAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(apiArticle: Article) {
            binding.textViewTitle.text = apiArticle.title
            binding.textViewDescription.text = apiArticle.description
            binding.textViewSource.text = apiArticle.source.name
            Glide.with(binding.imageViewBanner.context)
                .load(apiArticle.imageUrl)
                .into(binding.imageViewBanner)
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(apiArticle.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            TopHeadlineItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = apiArticleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(apiArticleList[position])

    fun addData(list: List<Article>) {

        apiArticleList.addAll(list)
    }

    fun replaceData(list: List<Article>) {
        apiArticleList.clear()
        apiArticleList.addAll(list)

    }
}