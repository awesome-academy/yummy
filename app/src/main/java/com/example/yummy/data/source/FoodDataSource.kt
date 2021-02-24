package com.example.yummy.data.source

import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.data.source.model.Favorite
import com.example.yummy.data.source.model.Food

interface FoodDataSource {
    interface Local {
        fun getAllFavorite(callback: OnDataLocalCallback<List<Favorite>>)
        fun addFavorite(food: Food, callback: OnDataLocalCallback<Boolean>)
        fun deleteFavorite(food: Food, callback: OnDataLocalCallback<Boolean>)
    }

    interface Remote {

    }
}
