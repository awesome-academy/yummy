package com.example.yummy.data.source.local

import com.example.yummy.data.source.LanguageDataSource
import com.example.yummy.utlis.LanguageConst.LANGUAGE_KEY
import com.example.yummy.utlis.SharedPreferencesHelper

class LanguageLocalDataSource private constructor(
    private val preferencesHelper: SharedPreferencesHelper
) : LanguageDataSource {

    override fun setLanguage(localKey: String) {
        preferencesHelper.put(LANGUAGE_KEY, localKey)
    }

    override fun getLanguage(): String {
        return preferencesHelper.get(LANGUAGE_KEY, String())
    }

    companion object {
        private var instance: LanguageLocalDataSource? = null
        fun getInstance(preferencesHelper: SharedPreferencesHelper) =
            instance ?: LanguageLocalDataSource(preferencesHelper).also { instance = it }
    }
}
