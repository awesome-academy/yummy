package com.example.yummy.data.repository

import com.example.yummy.data.model.News
import com.example.yummy.data.source.NewsDataSource
import com.example.yummy.data.source.remote.utlis.OnDataCallback

class NewsRepository private constructor(
    private val remote: NewsDataSource
) : NewsDataSource {

    override fun getNews(callback: OnDataCallback<List<News>>) {
        remote.getNews(callback)
    }

    companion object {
        private var instance: NewsRepository? = null
        fun getInstance(remote: NewsDataSource) =
            instance ?: NewsRepository(remote).also { instance = it }
    }
}
