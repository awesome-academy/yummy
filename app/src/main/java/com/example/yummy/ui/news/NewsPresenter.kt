package com.example.yummy.ui.news

import com.example.yummy.data.model.News
import com.example.yummy.data.repository.NewsRepository
import com.example.yummy.data.source.remote.utlis.OnDataCallback

class NewsPresenter(
    private val view: NewsContract.View,
    private val repository: NewsRepository
) : NewsContract.Presenter {

    override fun getNews() {
        view.showLoading()
        repository.getNews(object : OnDataCallback<List<News>> {
            override fun onSuccess(data: List<News>) {
                view.hideLoading()
                view.showNews(data)
            }

            override fun onFail(exception: Exception?) {
                view.showError(exception?.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun start() {
        getNews()
    }
}
