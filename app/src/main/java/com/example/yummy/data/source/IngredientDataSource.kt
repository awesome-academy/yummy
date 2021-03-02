package com.example.yummy.data.source

import com.example.yummy.data.model.Ingredient
import com.example.yummy.data.source.remote.utlis.OnDataCallback

interface IngredientDataSource {
    fun getIngredients(callback: OnDataCallback<List<Ingredient>>)
}
