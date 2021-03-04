package com.example.yummy.data.source.remote

import com.example.yummy.data.model.*
import com.example.yummy.data.source.MealDataSource
import com.example.yummy.data.source.remote.api.APIConstants.MEAL_NULL
import com.example.yummy.data.source.remote.api.APIQuery
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import com.example.yummy.data.source.remote.utlis.RemoteAsyncTask
import com.example.yummy.data.source.remote.utlis.readContentApi
import com.example.yummy.utlis.NameConst.MEALS
import com.example.yummy.utlis.parseJsonArray
import org.json.JSONObject

@Suppress("DEPRECATION")
class MealRemoteDataSource private constructor() : MealDataSource.Remote {

    override fun getMealByCategory(keyCategory: String, callback: OnDataCallback<List<Meal>>) {
        RemoteAsyncTask(callback) {
            getMealByCategory(keyCategory)
        }.execute()
    }

    override fun getMealByArea(keyArea: String, callback: OnDataCallback<List<Meal>>) {
        RemoteAsyncTask(callback) {
            getMealByArea(keyArea)
        }.execute()
    }

    override fun getMealByIngredient(keyIngredient: String, callback: OnDataCallback<List<Meal>>) {
        RemoteAsyncTask(callback) {
            getMealByIngredient(keyIngredient)
        }.execute()
    }

    override fun searchMeal(wordSearch: String, callback: OnDataCallback<List<Meal>>) {
        RemoteAsyncTask(callback) {
            getMealByWordSearch(wordSearch)
        }.execute()
    }

    private fun getMealByCategory(keyCategory: String): List<Meal> =
        JSONObject(readContentApi(APIQuery.queryMealByCategory(keyCategory))).getString(MEALS)
            .parseJsonArray()

    private fun getMealByIngredient(keyIngredient: String): List<Meal> =
        JSONObject(readContentApi(APIQuery.queryMealByIngredient(keyIngredient))).getString(MEALS)
            .parseJsonArray()

    private fun getMealByArea(keyArea: String): List<Meal> =
        JSONObject(readContentApi(APIQuery.queryMealByArea(keyArea))).getString(MEALS)
            .parseJsonArray()

    private fun getMealByWordSearch(wordSearch: String): List<Meal> {
        val listMeal = mutableListOf<Meal>()
        val json = readContentApi(APIQuery.querySearchMeal(wordSearch))
        return if (json == MEAL_NULL) {
            listMeal
        } else {
            JSONObject(json).getString(MEALS).parseJsonArray()
        }
    }

    companion object {
        private var instance: MealRemoteDataSource? = null
        fun getInstance() =
            instance ?: MealRemoteDataSource().also { instance = it }
    }
}
