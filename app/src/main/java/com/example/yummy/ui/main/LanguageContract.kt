package com.example.yummy.ui.main

import com.example.yummy.base.BasePresenter

interface LanguageContract {
    interface View{
        fun updateLanguage(language: String?)
        fun restartApp()
    }
    interface Presenter:BasePresenter{
        fun setLanguage(localKey: String)
        fun getLanguage()
    }
}
