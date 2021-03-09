package com.example.yummy.data.source.local.dao

import android.annotation.SuppressLint
import com.example.yummy.data.model.Meal
import com.example.yummy.data.model.Meal.Companion.FAVORITE_KEY_ID
import com.example.yummy.data.model.Meal.Companion.FAVORITE_TABLE_NAME
import com.example.yummy.data.model.Meal.Companion.FAVORITE_TIME_LONG
import com.example.yummy.data.source.local.db.AppDatabase

class FavoriteDaoImpl private constructor(database: AppDatabase) : FavoriteDao {

    private val writableDB = database.writableDatabase
    private val readableDB = database.readableDatabase

    override fun insertMeal(meal: Meal) =
        writableDB.insert(FAVORITE_TABLE_NAME, null, meal.getContentValue())

    override fun deleteMeal(idMeal: String) =
        writableDB.delete(FAVORITE_TABLE_NAME, "${FAVORITE_KEY_ID}=?", arrayOf(idMeal)) > 0


    override fun getAllMeals(): List<Meal> {
        val meals = mutableListOf<Meal>()
        val query = "SELECT * FROM $FAVORITE_TABLE_NAME"
        val cursor = readableDB.rawQuery(query, null)
        cursor.use {
            while (it.moveToNext()) {
                meals.add(Meal(it))
            }
        }
        return meals
    }

    override fun getNewMeals(): List<Meal> {
        val meals = mutableListOf<Meal>()
        val query = "SELECT * FROM $FAVORITE_TABLE_NAME ORDER BY $FAVORITE_TIME_LONG DESC LIMIT 5"
        val cursor = readableDB.rawQuery(query, null)
        cursor.use {
            while (it.moveToNext()) {
                meals.add(Meal(it))
            }
        }
        return meals
    }

    @SuppressLint("Recycle")
    override fun isFavorite(mealId: String): Int {
        val cursor = readableDB.query(
            FAVORITE_TABLE_NAME,
            null,
            "$FAVORITE_KEY_ID = ?",
            arrayOf(mealId),
            null,
            null,
            null
        )
        return cursor.count
    }

    companion object {
        private var instance: FavoriteDaoImpl? = null
        fun getInstance(database: AppDatabase): FavoriteDaoImpl =
            instance ?: FavoriteDaoImpl(database).also {
                instance = it
            }
    }
}
