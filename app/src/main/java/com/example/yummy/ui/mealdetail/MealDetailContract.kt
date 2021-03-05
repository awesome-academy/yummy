package com.example.yummy.ui.mealdetail

import com.example.yummy.base.BasePresenter
import com.example.yummy.base.BaseView
import com.example.yummy.data.model.*

interface MealDetailContract {
    interface View : BaseView {
        fun showMealDetailByMeal(mealDetails: List<MealDetail>)
        fun showMealFavorite(isFavorite: Int)
        fun isInsertedMealFavorite(long: Long)
        fun isDeletedMealFavorite(boolean: Boolean)
    }

    interface Presenter : BasePresenter {
        fun getMealDetailByMeal(meal: Meal)
        fun getMealFavorite(meal: Meal)
        fun insertMealFavorite(meal: Meal)
        fun deleteMealFavorite(mealId: String)
    }
}
