package com.example.yummy.data.source.remote

import com.example.yummy.data.model.News
import com.example.yummy.data.source.NewsDataSource
import com.example.yummy.data.source.remote.api.APIConstants
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import com.example.yummy.data.source.remote.utlis.RemoteAsyncTask
import com.example.yummy.data.source.remote.utlis.XMLDOMParser
import com.example.yummy.data.source.remote.utlis.readContentApi
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import java.util.regex.Matcher
import java.util.regex.Pattern

@Suppress("DEPRECATION")
class NewsRemoteDataSource private constructor() : NewsDataSource {

    override fun getNews(callback: OnDataCallback<List<News>>) {
        RemoteAsyncTask(callback) {
            getNews()
        }.execute()
    }

    private fun getNews(): List<News> {
        val listNews = mutableListOf<News>()
        val parser = XMLDOMParser()
        val document = parser.getDocument(readContentApi(APIConstants.RSS))
        val nodeListDescription =
            document?.getElementsByTagName(APIConstants.RSS_DESCRIPTION) as NodeList
        val nodeList = document.getElementsByTagName(APIConstants.RSS_ITEM) as NodeList
        val pattern = Pattern.compile(APIConstants.RSS_IMG_FORMAT)

        var title: String
        var link: String
        var img: String? = null
        var matcher: Matcher
        for (index in 0 until nodeList.length) {
            val element = nodeList.item(index) as Element
            title = parser.getValue(element, APIConstants.RSS_TITLE).toString()
            link = parser.getValue(element, APIConstants.RSS_LINK).toString()
            val elementDescription = nodeListDescription.item(index + 1).textContent
            val output = elementDescription.split(APIConstants.RSS_BR)
            matcher = pattern.matcher(elementDescription)
            if (matcher.find()) img = matcher.group(1)
            listNews.add(News(title, link, img, output[1]))
        }
        return listNews
    }

    companion object {
        private var instance: NewsRemoteDataSource? = null
        fun getInstance() =
            instance ?: NewsRemoteDataSource().also { instance = it }
    }
}
