package com.example.yummy.data.source.local

import com.example.yummy.data.model.Meal
import com.example.yummy.data.source.MealDataSource
import com.example.yummy.data.source.local.dao.FavoriteDao
import com.example.yummy.data.source.local.utils.LocalAsyncTask
import com.example.yummy.data.source.local.utils.OnDataLocalCallback

@Suppress("DEPRECATION")
class MealLocalDataSource private constructor(
    private val favoriteDao: FavoriteDao
) : MealDataSource.Local {

    override fun insertMeal(meal: Meal, callback: OnDataLocalCallback<Long>) {
        LocalAsyncTask<Meal, Long>(callback) {
            favoriteDao.insertMeal(meal)
        }.execute(meal)
    }

    override fun deleteMeal(mealId: String, callback: OnDataLocalCallback<Boolean>) {
        LocalAsyncTask<String, Boolean>(callback) {
            favoriteDao.deleteMeal(mealId)
        }.execute(mealId)
    }

    override fun getAllMeals(callback: OnDataLocalCallback<List<Meal>>) {
        LocalAsyncTask<Unit, List<Meal>>(callback) {
            favoriteDao.getAllMeals()
        }.execute(Unit)
    }

    override fun getNewMeals(callback: OnDataLocalCallback<List<Meal>>) {
        LocalAsyncTask<Unit, List<Meal>>(callback) {
            favoriteDao.getNewMeals()
        }.execute(Unit)
    }

    override fun isFavorite(mealId: String, callback: OnDataLocalCallback<Int>) {
        LocalAsyncTask<String, Int>(callback) {
            favoriteDao.isFavorite(mealId)
        }.execute(mealId)
    }

    companion object {
        private var instance: MealLocalDataSource? = null
        fun getInstance(favoriteDao: FavoriteDao) =
            instance ?: MealLocalDataSource(favoriteDao).also { instance = it }
    }
}
