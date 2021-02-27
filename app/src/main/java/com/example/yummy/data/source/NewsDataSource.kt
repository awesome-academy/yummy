package com.example.yummy.data.source

import com.example.yummy.data.model.News
import com.example.yummy.data.source.remote.utlis.OnDataCallback

interface NewsDataSource {
    fun getNews(callback: OnDataCallback<List<News>>)
}
