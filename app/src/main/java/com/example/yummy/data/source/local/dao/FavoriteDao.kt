package com.example.yummy.data.source.local.dao

import com.example.yummy.data.model.Meal

interface FavoriteDao {
    fun insertMeal(meal: Meal): Long
    fun deleteMeal(idMeal: String): Boolean
    fun getAllMeals(): List<Meal>
    fun getNewMeals(): List<Meal>
    fun isFavorite(mealId: String): Int
}
