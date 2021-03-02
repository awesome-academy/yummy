package com.example.yummy.data.repository

import com.example.yummy.data.model.Category
import com.example.yummy.data.source.CategoryDataSource
import com.example.yummy.data.source.remote.utlis.OnDataCallback

class CategoryRepository private constructor(
    private val remote: CategoryDataSource
) : CategoryDataSource {

    override fun getCategories(callback: OnDataCallback<List<Category>>) {
        remote.getCategories(callback)
    }

    companion object {
        private var instance: CategoryRepository? = null
        fun getInstance(remote: CategoryDataSource) =
            instance ?: CategoryRepository(remote).also { instance = it }
    }
}
