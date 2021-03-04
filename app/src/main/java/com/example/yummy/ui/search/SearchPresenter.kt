package com.example.yummy.ui.search

import com.example.yummy.data.model.Meal
import com.example.yummy.data.repository.MealRepository
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import java.lang.Exception

class SearchPresenter(
    private val view: SearchContract.View,
    private val repository: MealRepository
) : SearchContract.Presenter {

    override fun getMealsByWordSearch(wordSearch: String) {
        view.showLoading()
        repository.searchMeal(wordSearch, object : OnDataCallback<List<Meal>> {
            override fun onSuccess(data: List<Meal>) {
                if (data.isEmpty()) {
                    view.showNotFound()
                } else {
                    view.hideNotFound()
                }
                view.hideLoading()
                view.showMealsByWordSearch(data)
            }

            override fun onFail(exception: Exception?) {
                view.showError(exception?.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun start() {
        getMealsByWordSearch("")
    }
}
