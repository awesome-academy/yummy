package com.example.yummy.data.source.remote

import com.example.yummy.data.model.Area
import com.example.yummy.data.source.AreaDataSource
import com.example.yummy.data.source.remote.utlis.OnDataCallback

class AreaRemoteDataSource private constructor() : AreaDataSource {

    override fun getArea(callback: OnDataCallback<List<Area>>) {
    }

    companion object {
        private var instance: AreaRemoteDataSource? = null
        fun getInstance() =
            instance ?: AreaRemoteDataSource().also { instance = it }
    }
}
