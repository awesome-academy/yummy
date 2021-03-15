package com.example.yummy.ui.news

import com.example.yummy.data.model.News
import com.example.yummy.data.repository.NewsRepository
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import io.mockk.*
import org.junit.Test
import java.lang.Exception

class NewsPresenterTest {

    private val view = mockk<NewsContract.View>(relaxed = true)
    private val repository = mockk<NewsRepository>()
    private val newsPresenter = NewsPresenter(view, repository)
    private val callback = slot<OnDataCallback<List<News>>>()

    @Test
    fun `getAreas callback return onSuccess`() {
        val news = mutableListOf<News>()
        every {
            repository.getNews(capture(callback))
        } answers {
            callback.captured.onSuccess(news)
        }
        newsPresenter.getNews()
        verify {
            view.showLoading()
            view.hideLoading()
            view.showNews(news)
        }
    }

    @Test
    fun `getAreas callback return onFail, exception is not null`() {
        val exception = Exception()
        every {
            repository.getNews(capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }
        newsPresenter.getNews()
        verify {
            view.showLoading()
            view.showError(exception.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `getAreas callback return onFail, exception is null`() {
        val exception: Exception? = null
        every {
            repository.getNews(capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }
        newsPresenter.getNews()
        verify {
            view.showLoading()
            view.showError(exception?.message.toString())
            view.hideLoading()
        }
    }
}
