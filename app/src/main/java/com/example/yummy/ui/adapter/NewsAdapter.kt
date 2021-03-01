package com.example.yummy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yummy.R
import com.example.yummy.data.model.News

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    var onItemClick: (News) -> Unit = { _ -> }
    private val news = mutableListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view, onItemClick)
    }

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(news[position])
    }

    fun updateData(newList: List<News>) {
        news.run {
            clear()
            addAll(newList)
            notifyDataSetChanged()
        }
    }
}
