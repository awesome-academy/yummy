package com.example.yummy.data.repository

import com.example.yummy.data.model.Ingredient
import com.example.yummy.data.source.IngredientDataSource
import com.example.yummy.data.source.remote.utlis.OnDataCallback

class IngredientRepository private constructor(
    private val remote: IngredientDataSource
) : IngredientDataSource {

    override fun getIngredients(callback: OnDataCallback<List<Ingredient>>) {
        remote.getIngredients(callback)
    }

    companion object {
        private var instance: IngredientRepository? = null
        fun getInstance(remote: IngredientDataSource) =
            instance ?: IngredientRepository(remote).also { instance = it }
    }
}
