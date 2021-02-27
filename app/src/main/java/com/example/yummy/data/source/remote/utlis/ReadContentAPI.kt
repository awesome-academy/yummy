package com.example.yummy.data.source.remote.utlis

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun readContentApi(link: String): String {
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
