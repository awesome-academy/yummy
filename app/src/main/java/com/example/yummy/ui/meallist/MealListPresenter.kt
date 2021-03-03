package com.example.yummy.ui.meallist

import com.example.yummy.data.model.Area
import com.example.yummy.data.model.Category
import com.example.yummy.data.model.Ingredient
import com.example.yummy.data.model.Meal
import com.example.yummy.data.repository.MealRepository
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import java.lang.Exception

class MealListPresenter(
    private val view: MealListContract.View,
    private val repository: MealRepository
) : MealListContract.Presenter {

    override fun getMealsByCategory(category: Category) {
        view.showLoading()
        repository.getMealByCategory(category.name, object : OnDataCallback<List<Meal>> {
            override fun onSuccess(data: List<Meal>) {
                view.showMeals(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception?) {
                view.showError(exception?.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun getMealsByIngredient(ingredient: Ingredient) {
        view.showLoading()
        repository.getMealByIngredient(ingredient.name, object : OnDataCallback<List<Meal>> {
            override fun onSuccess(data: List<Meal>) {
                view.showMeals(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception?) {
                view.showError(exception?.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun getMealsByArea(area: Area) {
        view.showLoading()
        repository.getMealByArea(area.name, object : OnDataCallback<List<Meal>> {
            override fun onSuccess(data: List<Meal>) {
                view.showMeals(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception?) {
                view.showError(exception?.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun start() {

    }
}
