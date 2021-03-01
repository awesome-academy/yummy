package com.example.yummy.base

interface BaseView {
    fun showError(message: String)
    fun showLoading()
    fun hideLoading()
}
