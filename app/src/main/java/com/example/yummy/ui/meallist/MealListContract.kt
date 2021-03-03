package com.example.yummy.ui.meallist

import com.example.yummy.base.BasePresenter
import com.example.yummy.base.BaseView
import com.example.yummy.data.model.Area
import com.example.yummy.data.model.Category
import com.example.yummy.data.model.Ingredient
import com.example.yummy.data.model.Meal

interface MealListContract {
    interface View : BaseView {
        fun showMeals(meals: List<Meal>)
    }

    interface Presenter : BasePresenter {
        fun getMealsByCategory(category: Category)
        fun getMealsByIngredient(ingredient: Ingredient)
        fun getMealsByArea(area: Area)
    }
}
