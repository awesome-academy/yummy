package com.example.yummy.data.source.remote

import com.example.yummy.data.model.*
import com.example.yummy.data.source.FoodDataSource
import com.example.yummy.data.source.remote.api.APIConstants.RSS
import com.example.yummy.data.source.remote.api.APIConstants.RSS_BR
import com.example.yummy.data.source.remote.api.APIConstants.RSS_DESCRIPTION
import com.example.yummy.data.source.remote.api.APIConstants.RSS_IMG_FORMAT
import com.example.yummy.data.source.remote.api.APIConstants.RSS_ITEM
import com.example.yummy.data.source.remote.api.APIConstants.RSS_LINK
import com.example.yummy.data.source.remote.api.APIConstants.RSS_TITLE
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import com.example.yummy.data.source.remote.utlis.XMLDOMParser
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.regex.Matcher
import java.util.regex.Pattern

class FoodRemoteDataSource : FoodDataSource.Remote {
    override fun getCategory(callback: OnDataCallback<List<Category>>) {

    }

    override fun getArea(callback: OnDataCallback<List<Area>>) {

    }

    override fun getIngredient(callback: OnDataCallback<List<Ingredient>>) {

    }

    override fun getNews(callback: OnDataCallback<List<News>>) {

    }

    override fun getMealByCategory(meal: String, callback: OnDataCallback<List<Meal>>) {

    }

    override fun getMealByArea(meal: String, callback: OnDataCallback<List<Meal>>) {

    }

    override fun getMealByIngredient(meal: String, callback: OnDataCallback<List<Meal>>) {

    }

    override fun searchMeal(wordSearch: String, callback: OnDataCallback<List<Meal>>) {

    }

    private fun getNews(): List<News> {
        val listNews = mutableListOf<News>()
        val parser = XMLDOMParser()
        val document = parser.getDocument(readContentApi(RSS))
        val nodeListDescription = document?.getElementsByTagName(RSS_DESCRIPTION) as NodeList
        val nodeList = document.getElementsByTagName(RSS_ITEM) as NodeList
        val pattern = Pattern.compile(RSS_IMG_FORMAT)

        var title: String
        var link: String
        var img: String? = null
        var matcher: Matcher
        for (index in 0 until nodeList.length) {
            val element = nodeList.item(index) as Element
            title = parser.getValue(element, RSS_TITLE).toString()
            link = parser.getValue(element, RSS_LINK).toString()
            val elementDescription = nodeListDescription.item(index + 1).textContent
            val output = elementDescription.split(RSS_BR)
            matcher = pattern.matcher(elementDescription)
            if (matcher.find()) img = matcher.group(1)
            listNews.add(News(title, link, img, output[1]))
        }
        return listNews
    }

    private fun readContentApi(link: String): String {
        val stringBuilder = StringBuilder()
        try {
            val url = URL(link)
            val urlOpenConnection = url.openConnection() as HttpURLConnection
            val inputStreamReader = InputStreamReader(urlOpenConnection.inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            bufferedReader.forEachLine {
                stringBuilder.append(it)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }

    companion object {
        private var instance: FoodRemoteDataSource? = null
        fun getInstance() =
            instance ?: FoodRemoteDataSource().also { instance = it }
    }
}
