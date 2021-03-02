package com.example.yummy.data.source

import com.example.yummy.data.model.Category
import com.example.yummy.data.source.remote.utlis.OnDataCallback

interface CategoryDataSource {
    fun getCategories(callback: OnDataCallback<List<Category>>)
}
