package com.example.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.domain.models.News

interface NewsAdapterDelegate {
    fun onNewsClicked(news: News)
}

class NewsRecyclerViewAdapter(private var delegate: NewsAdapterDelegate) :
    ListAdapter<News, NewsRecyclerViewAdapter.ViewHolder>(
        diffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), delegate
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.news = getItem(position)
    }


    class ViewHolder(
        private val binding: ItemNewsBinding,
        private var delegate: NewsAdapterDelegate
    ) :
        RecyclerView.ViewHolder(binding.root) {

        var news: News? = null
            set(value) {
                value?.let { newValue ->
                    field = newValue
                    binding.apply {
                        Glide.with(root.context).load(newValue.image).into(image)
                        title.text = newValue.header
                        description.text = newValue.description
                        source.text = newValue.source
                        date.text = newValue.publishedAt

                        root.setOnClickListener {
                            delegate.onNewsClicked(newValue)
                        }
                    }

                }
            }
    }


    companion object {
        var diffUtil = object : DiffUtil.ItemCallback<News>() {

            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.header == newItem.header
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }
    }


}