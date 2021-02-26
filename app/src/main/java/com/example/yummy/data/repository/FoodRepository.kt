package com.example.yummy.data.repository

import com.example.yummy.data.model.*
import com.example.yummy.data.source.FoodDataSource
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.data.source.remote.utlis.OnDataCallback

class FoodRepository private constructor(
    private val local: FoodDataSource.Local,
    private val remote: FoodDataSource.Remote
) : FoodDataSource.Remote, FoodDataSource.Local {

    override fun getCategory(callback: OnDataCallback<List<Category>>) {
        remote.getCategory(callback)
    }

    override fun getArea(callback: OnDataCallback<List<Area>>) {
        remote.getArea(callback)
    }

    override fun getIngredient(callback: OnDataCallback<List<Ingredient>>) {
        remote.getIngredient(callback)
    }

    override fun getNews(callback: OnDataCallback<List<News>>) {
        remote.getNews(callback)
    }

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
        private var instance: FoodRepository? = null
        fun getInstance(local: FoodDataSource.Local, remote: FoodDataSource.Remote) =
            instance ?: FoodRepository(local, remote).also { instance = it }
    }
}
