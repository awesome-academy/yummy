package com.example.yummy.data.source.remote

import com.example.yummy.data.model.Category
import com.example.yummy.data.source.CategoryDataSource
import com.example.yummy.data.source.remote.utlis.OnDataCallback

class CategoryRemoteDataSource private constructor() : CategoryDataSource {

    override fun getCategory(callback: OnDataCallback<List<Category>>) {
    }

    companion object {
        private var instance: CategoryRemoteDataSource? = null
        fun getInstance() =
            instance ?: CategoryRemoteDataSource().also { instance = it }
    }
}
