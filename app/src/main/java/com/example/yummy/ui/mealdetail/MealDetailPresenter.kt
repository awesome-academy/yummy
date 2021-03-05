package com.example.yummy.ui.mealdetail

import com.example.yummy.data.model.Meal
import com.example.yummy.data.model.MealDetail
import com.example.yummy.data.repository.MealRepository
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import java.lang.Exception

class MealDetailPresenter(
    private val view: MealDetailContract.View,
    private val repository: MealRepository
) : MealDetailContract.Presenter {

    override fun getMealDetailByMeal(meal: Meal) {
        view.showLoading()
        repository.getMealDetailByMeal(meal.name, object : OnDataCallback<List<MealDetail>> {
            override fun onSuccess(data: List<MealDetail>) {
                view.showMealDetailByMeal(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception?) {
                view.showError(exception?.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun getMealFavorite(meal: Meal) {
        view.showLoading()
        repository.isFavorite(meal.id, object : OnDataLocalCallback<Int> {
            override fun onSuccess(data: Int) {
                view.showMealFavorite(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun insertMealFavorite(meal: Meal) {
        view.showLoading()
        repository.insertMeal(meal, object : OnDataLocalCallback<Long> {
            override fun onSuccess(data: Long) {
                view.isInsertedMealFavorite(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun deleteMealFavorite(mealId: String) {
        view.showLoading()
        repository.deleteMeal(mealId, object : OnDataLocalCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.isDeletedMealFavorite(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun start() {

    }
}
