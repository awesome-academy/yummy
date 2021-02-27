package com.example.yummy.data.source.remote

import com.example.yummy.data.model.*
import com.example.yummy.data.source.MealDataSource
import com.example.yummy.data.source.remote.utlis.OnDataCallback

class MealRemoteDataSource private constructor() : MealDataSource.Remote {

    override fun getMealByCategory(meal: String, callback: OnDataCallback<List<Meal>>) {
    }

    override fun getMealByArea(meal: String, callback: OnDataCallback<List<Meal>>) {
    }

    override fun getMealByIngredient(meal: String, callback: OnDataCallback<List<Meal>>) {
    }

    override fun searchMeal(wordSearch: String, callback: OnDataCallback<List<Meal>>) {
    }

    companion object {
        private var instance: MealRemoteDataSource? = null
        fun getInstance() =
            instance ?: MealRemoteDataSource().also { instance = it }
    }
}
