package com.example.yummy.data.repository

import com.example.yummy.data.model.*
import com.example.yummy.data.source.FoodDataSource
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

    companion object {
        private var instance: FoodRepository? = null
        fun getInstance(local: FoodDataSource.Local, remote: FoodDataSource.Remote) =
            instance ?: FoodRepository(local, remote).also { instance = it }
    }
}
