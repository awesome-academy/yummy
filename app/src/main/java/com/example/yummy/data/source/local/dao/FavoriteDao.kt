package com.example.yummy.data.source.local.dao

import com.example.yummy.data.source.model.Favorite
import com.example.yummy.data.source.model.Food

interface FavoriteDao {
    fun getAllFavorite() : List<Favorite>
    fun addFavorite(food: Food) : Boolean
    fun deleteFavorite(food: Food): Boolean
}
