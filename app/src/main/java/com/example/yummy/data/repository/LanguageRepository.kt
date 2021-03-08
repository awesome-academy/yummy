package com.example.yummy.data.repository

import com.example.yummy.data.source.LanguageDataSource

class LanguageRepository private constructor(
    private val local: LanguageDataSource
) : LanguageDataSource {

    override fun setLanguage(localKey: String) {
        local.setLanguage(localKey)
    }

    override fun getLanguage() = local.getLanguage()

    companion object {
        private var instance: LanguageRepository? = null
        fun getInstance(local: LanguageDataSource) =
            instance ?: LanguageRepository(local).also { instance = it }
    }
}
