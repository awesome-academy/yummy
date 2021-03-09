package com.example.yummy.ui.favorite.allfavorite

import com.example.yummy.data.model.Meal
import com.example.yummy.data.repository.MealRepository
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.utlis.sortMealByAlphabet
import java.lang.Exception

class AllMealPresenter(
    private val view: AllMealContract.View,
    private val repository: MealRepository
) : AllMealContract.Presenter {

    override fun getAllMealFavorite() {
        view.showLoading()
        repository.getAllMeals(object : OnDataLocalCallback<List<Meal>> {
            override fun onSuccess(data: List<Meal>) {
                view.showAllMealFavorite(data.sortMealByAlphabet())
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
            }
        })
    }

    override fun start() {
        getAllMealFavorite()
    }
}
