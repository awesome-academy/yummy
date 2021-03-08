package com.example.yummy.ui.news

import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.data.model.News
import com.example.yummy.ui.adapter.NewsAdapter
import com.example.yummy.ui.dialog.LoadingDialog
import com.example.yummy.utlis.*
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment(), NewsContract.View {

    private val newsAdapter = NewsAdapter()
    private var newsPresenter: NewsPresenter? = null
    private var loadingDialog: LoadingDialog? = null
    private var isConnection = false

    override val layoutResource get() = R.layout.fragment_news

    override fun setupViews() {
        initAdapter()
        initDialog()
    }

    override fun initData() {
        val repository = RepositoryUtils.getNewsRepository()
        newsPresenter = NewsPresenter(this, repository)
        val context = context ?: return
        isConnection = NetworkUtil.isConnection(context)
        if (!isConnection) {
            view?.showSnackBar(getString(R.string.msg_check_internet))
            return
        }
        newsPresenter?.start()
    }

    override fun initActions() {
    }

    override fun showNews(listNews: List<News>) {
        newsAdapter.updateData(listNews)
    }

    override fun showError(message: String) {
        context?.showToast(message)
    }

    override fun showLoading() {
        loadingDialog?.show()
    }

    override fun hideLoading() {
        loadingDialog?.hide()
    }

    private fun initAdapter() {
        recyclerNews.adapter = newsAdapter.apply {
            onItemClick = { item ->
                clickItem(item)
            }
        }
    }

    private fun initDialog() {
        context?.let { loadingDialog = LoadingDialog(it) }
    }

    private fun clickItem(news: News) {
        context?.let {
            startActivity(NewsActivity.getIntent(it, news.link))
        }
    }
}
