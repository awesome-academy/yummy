package com.example.yummy.ui.news

import com.example.yummy.base.BasePresenter
import com.example.yummy.base.BaseView
import com.example.yummy.data.model.News

interface NewsContract {
    interface View : BaseView {
        fun showNews(listNews: List<News>)
    }

    interface Presenter : BasePresenter {
        fun getNews()
    }
}
