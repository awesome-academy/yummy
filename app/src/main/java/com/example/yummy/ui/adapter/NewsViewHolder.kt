package com.example.yummy.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yummy.data.model.News
import com.example.yummy.utlis.loadImage
import kotlinx.android.synthetic.main.item_news.view.*

class NewsViewHolder(
    itemView: View,
    private var itemClick: (News) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var itemData: News? = null

    init {
        itemView.setOnClickListener {
            itemData?.let {
                itemClick(it)
            }
        }
    }

    fun bindData(news: News) {
        itemData = news
        itemView.apply {
            textTitle.text = news.title
            textDescription.text = news.description
            news.image?.let { imageNews.loadImage(it) }
        }
    }
}
