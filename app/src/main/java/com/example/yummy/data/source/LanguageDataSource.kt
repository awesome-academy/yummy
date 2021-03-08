package com.example.yummy.data.source

interface LanguageDataSource {
    fun setLanguage(localKey: String)
    fun getLanguage(): String
}
