package com.example.yummy.data.source.remote

import android.util.JsonReader
import com.example.yummy.data.model.Category
import com.example.yummy.data.source.CategoryDataSource
import com.example.yummy.data.source.remote.api.APIConstants
import com.example.yummy.data.source.remote.api.APIQuery
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import com.example.yummy.data.source.remote.utlis.RemoteAsyncTask
import com.example.yummy.data.source.remote.utlis.readContentApi
import com.example.yummy.utlis.NameConst
import com.example.yummy.utlis.parseJsonArray
import org.json.JSONObject

class CategoryRemoteDataSource private constructor() : CategoryDataSource {

    override fun getCategories(callback: OnDataCallback<List<Category>>) {
        RemoteAsyncTask(callback) {
            getCategories()
        }.execute()
    }

    private fun getCategories(): List<Category> =
        JSONObject(readContentApi(APIQuery.queryCategory())).getString(NameConst.CATEGORIES)
            .parseJsonArray()

    companion object {
        private var instance: CategoryRemoteDataSource? = null
        fun getInstance() =
            instance ?: CategoryRemoteDataSource().also { instance = it }
    }
}
