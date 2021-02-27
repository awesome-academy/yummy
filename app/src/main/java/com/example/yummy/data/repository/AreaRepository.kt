package com.example.yummy.data.repository

import com.example.yummy.data.model.Area
import com.example.yummy.data.source.AreaDataSource
import com.example.yummy.data.source.remote.utlis.OnDataCallback

class AreaRepository private constructor(
    private val remote: AreaDataSource
) : AreaDataSource {

    override fun getArea(callback: OnDataCallback<List<Area>>) {
        remote.getArea(callback)
    }

    companion object {
        private var instance: AreaRepository? = null
        fun getInstance(remote: AreaDataSource) =
            instance ?: AreaRepository(remote).also { instance = it }
    }
}
