package com.example.yummy.ui.main

import com.example.yummy.data.repository.LanguageRepository

class LanguagePresenter(
    private val view: LanguageContract.View,
    private val repository: LanguageRepository
) : LanguageContract.Presenter {

    override fun setLanguage(localKey: String) {
        repository.setLanguage(localKey)
        view.updateLanguage(repository.getLanguage())
        view.restartApp()
    }

    override fun getLanguage() {
        view.updateLanguage(repository.getLanguage())
    }

    override fun start() {
        getLanguage()
    }
}
