package com.example.yummy.data.source.remote

import com.example.yummy.data.model.Ingredient
import com.example.yummy.data.source.IngredientDataSource
import com.example.yummy.data.source.remote.api.APIConstants
import com.example.yummy.data.source.remote.api.APIQuery
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import com.example.yummy.data.source.remote.utlis.RemoteAsyncTask
import com.example.yummy.data.source.remote.utlis.readContentApi
import com.example.yummy.utlis.IngredientModelConst
import com.example.yummy.utlis.NameConst
import com.example.yummy.utlis.parseJsonArray
import org.json.JSONObject

class IngredientRemoteDataSource private constructor() : IngredientDataSource {

    override fun getIngredients(callback: OnDataCallback<List<Ingredient>>) {
        RemoteAsyncTask(callback){
            getIngredients()
        }.execute()
    }

    private fun getIngredients(): List<Ingredient> =
        JSONObject(readContentApi(APIQuery.queryIngredient())).getString(NameConst.MEALS).parseJsonArray()

    companion object {
        private var instance: IngredientRemoteDataSource? = null
        fun getInstance() =
            instance ?: IngredientRemoteDataSource().also { instance = it }
    }
}
