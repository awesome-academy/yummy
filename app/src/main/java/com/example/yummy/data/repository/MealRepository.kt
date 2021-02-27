package com.example.yummy.data.repository

import com.example.yummy.data.model.*
import com.example.yummy.data.source.MealDataSource
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.data.source.remote.utlis.OnDataCallback

class MealRepository private constructor(
    private val local: MealDataSource.Local,
    private val remote: MealDataSource.Remote
) : MealDataSource.Remote, MealDataSource.Local {

    override fun getMealByCategory(meal: String, callback: OnDataCallback<List<Meal>>) {
        remote.getMealByCategory(meal, callback)
    }

    override fun getMealByArea(meal: String, callback: OnDataCallback<List<Meal>>) {
        remote.getMealByArea(meal, callback)
    }

    override fun getMealByIngredient(meal: String, callback: OnDataCallback<List<Meal>>) {
        remote.getMealByIngredient(meal, callback)
    }

    override fun searchMeal(wordSearch: String, callback: OnDataCallback<List<Meal>>) {
        remote.searchMeal(wordSearch, callback)
    }

    override fun insertMeal(meal: Meal, callback: OnDataLocalCallback<Long>) {
        local.insertMeal(meal, callback)
    }

    override fun deleteMeal(mealId: String, callback: OnDataLocalCallback<Boolean>) {
        local.deleteMeal(mealId, callback)
    }

    override fun getAllMeals(callback: OnDataLocalCallback<List<Meal>>) {
        local.getAllMeals(callback)
    }

    override fun getNewMeals(callback: OnDataLocalCallback<List<Meal>>) {
        local.getNewMeals(callback)
    }

    override fun isFavorite(mealId: String, callback: OnDataLocalCallback<Int>) {
        local.isFavorite(mealId, callback)
    }

    companion object {
        private var instance: MealRepository? = null
        fun getInstance(local: MealDataSource.Local, remote: MealDataSource.Remote) =
            instance ?: MealRepository(local, remote).also { instance = it }
    }
}
