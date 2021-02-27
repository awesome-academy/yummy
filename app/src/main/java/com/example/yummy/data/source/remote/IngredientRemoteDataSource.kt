package com.example.yummy.data.source.remote

import com.example.yummy.data.model.Ingredient
import com.example.yummy.data.source.IngredientDataSource
import com.example.yummy.data.source.remote.utlis.OnDataCallback

class IngredientRemoteDataSource private constructor() : IngredientDataSource {

    override fun getIngredient(callback: OnDataCallback<List<Ingredient>>) {
    }

    companion object {
        private var instance: IngredientRemoteDataSource? = null
        fun getInstance() =
            instance ?: IngredientRemoteDataSource().also { instance = it }
    }
}
