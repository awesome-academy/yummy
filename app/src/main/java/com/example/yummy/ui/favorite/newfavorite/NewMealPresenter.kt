package com.example.yummy.ui.favorite.newfavorite

import com.example.yummy.data.model.Meal
import com.example.yummy.data.repository.MealRepository
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.utlis.sortMealByAlphabet
import java.lang.Exception

class NewMealPresenter(
    private val view: NewMealContract.View,
    private val repository: MealRepository
) : NewMealContract.Presenter {

    override fun getNewMealFavorite() {
        view.showLoading()
        repository.getNewMeals(object : OnDataLocalCallback<List<Meal>> {
            override fun onSuccess(data: List<Meal>) {
                view.showNewMealFavorite(data.sortMealByAlphabet())
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun start() {
        getNewMealFavorite()
    }
}
