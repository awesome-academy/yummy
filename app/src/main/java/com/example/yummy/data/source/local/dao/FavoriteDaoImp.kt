package com.example.yummy.data.source.local.dao

import com.example.yummy.data.source.local.db.YummyDatabase
import com.example.yummy.data.source.model.Favorite
import com.example.yummy.data.source.model.Food

class FavoriteDaoImp(database: YummyDatabase) : FavoriteDao {
    private val writableDB = database.writableDatabase
    private val readableDB = database.readableDatabase

    override fun getAllFavorite(): List<Favorite> {
        val favorites = mutableListOf<Favorite>()
        val cursor = readableDB.query(
            Favorite.FAVORITE_TABLE,
            arrayOf(Favorite.FAVORITE_ID, Favorite.FAVORITE_FOOD_ID, Favorite.FAVORITE_TIME),
            null, null, null, null, "${Favorite.FAVORITE_TIME} DESC"
        )

        if (cursor.moveToFirst()) {
            do {
                val favorite = Favorite(cursor)
                favorites.add(favorite)
            } while (cursor.moveToNext())
        }
        return favorites
    }

    override fun addFavorite(food: Food): Boolean {
        if (getAllFavorite().any { it.foodId == food.id }) return false
        val favorite = Favorite(foodId = food.id)
        val result = writableDB.insert(
            Favorite.FAVORITE_TABLE,
            null,
            favorite.getContentValue()
        )
        writableDB.close()
        return result > 0
    }

    override fun deleteFavorite(food: Food): Boolean {
        if (getAllFavorite().none { it.foodId == food.id }) return false
        val result = writableDB.delete(
            Favorite.FAVORITE_TABLE,
            "${Favorite.FAVORITE_FOOD_ID} =?",
            arrayOf(food.id.toString())
        )
        writableDB.close()
        return result > 0
    }

    companion object {
        private var instance: FavoriteDaoImp? = null

        fun getInstance(database: YummyDatabase):
                FavoriteDaoImp =
            instance ?: synchronized(this) {
                instance ?: FavoriteDaoImp(database).also {
                    instance = it
                }
            }
    }
}
