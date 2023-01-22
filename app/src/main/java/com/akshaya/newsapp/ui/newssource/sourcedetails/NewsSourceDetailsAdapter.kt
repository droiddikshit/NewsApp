package com.akshaya.newsapp.ui.newssource.sourcedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshaya.newsapp.data.model.Article
import com.akshaya.newsapp.databinding.NewsSrcDetailsUiBinding
import com.bumptech.glide.Glide

class NewsSourceDetailsAdapter(
    private val newsSourceList: ArrayList<Article>
) : RecyclerView.Adapter<NewsSourceDetailsAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: NewsSrcDetailsUiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(source: Article) {
            binding.textViewTitle.text = source.title
            Glide.with(binding.imageViewBanner.context)
                .load(source.imageUrl)
                .into(binding.imageViewBanner)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            NewsSrcDetailsUiBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = newsSourceList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(newsSourceList[position])

    fun addData(list: List<Article>) {
        newsSourceList.addAll(list)
    }

}