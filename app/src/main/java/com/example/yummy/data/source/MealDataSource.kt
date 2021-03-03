package com.example.yummy.data.source

import com.example.yummy.data.model.*
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.data.source.remote.utlis.OnDataCallback

interface MealDataSource {
    interface Local {
        fun insertMeal(meal: Meal, callback: OnDataLocalCallback<Long>)
        fun deleteMeal(mealId: String, callback: OnDataLocalCallback<Boolean>)
        fun getAllMeals(callback: OnDataLocalCallback<List<Meal>>)
        fun getNewMeals(callback: OnDataLocalCallback<List<Meal>>)
        fun isFavorite(mealId: String, callback: OnDataLocalCallback<Int>)
    }

    interface Remote {
        fun getMealByCategory(keyCategory: String, callback: OnDataCallback<List<Meal>>)
        fun getMealByArea(keyArea: String, callback: OnDataCallback<List<Meal>>)
        fun getMealByIngredient(keyIngredient: String, callback: OnDataCallback<List<Meal>>)
        fun searchMeal(wordSearch: String, callback: OnDataCallback<List<Meal>>)
    }
}
