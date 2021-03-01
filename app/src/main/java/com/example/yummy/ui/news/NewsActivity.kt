package com.example.yummy.ui.news

import android.content.Context
import android.content.Intent
import android.webkit.WebViewClient
import com.example.yummy.R
import com.example.yummy.base.BaseActivity
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : BaseActivity() {
    override val layoutResource get() = R.layout.activity_news

    override fun initComponents() {
        val url = intent?.getStringExtra(EXTRA_LINK_WEB)
        webViewNews.webViewClient = WebViewClient()
        url?.let { webViewNews.loadUrl(it) }
    }

    companion object {
        private const val EXTRA_LINK_WEB = "EXTRA_LINK_WEB"
        fun getIntent(context: Context, link: String) =
            Intent(context, NewsActivity::class.java).putExtra(EXTRA_LINK_WEB, link)
    }
}
