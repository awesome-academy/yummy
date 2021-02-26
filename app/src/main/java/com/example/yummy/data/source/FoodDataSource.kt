package com.example.yummy.data.source

import com.example.yummy.data.model.*
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.data.source.remote.utlis.OnDataCallback

interface FoodDataSource {
    interface Local {
        fun insertMeal(meal: Meal, callback: OnDataLocalCallback<Long>)
        fun deleteMeal(mealId: String, callback: OnDataLocalCallback<Boolean>)
        fun getAllMeals(callback: OnDataLocalCallback<List<Meal>>)
        fun getNewMeals(callback: OnDataLocalCallback<List<Meal>>)
        fun isFavorite(mealId: String, callback: OnDataLocalCallback<Int>)
    }

    interface Remote {
        fun getCategory(callback: OnDataCallback<List<Category>>)
        fun getArea(callback: OnDataCallback<List<Area>>)
        fun getIngredient(callback: OnDataCallback<List<Ingredient>>)
        fun getNews(callback: OnDataCallback<List<News>>)
        fun getMealByCategory(meal: String, callback: OnDataCallback<List<Meal>>)
        fun getMealByArea(meal: String, callback: OnDataCallback<List<Meal>>)
        fun getMealByIngredient(meal: String, callback: OnDataCallback<List<Meal>>)
        fun searchMeal(wordSearch: String, callback: OnDataCallback<List<Meal>>)
    }
}
