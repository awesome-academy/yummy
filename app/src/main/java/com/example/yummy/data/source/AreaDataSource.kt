package com.example.yummy.data.source

import com.example.yummy.data.model.Area
import com.example.yummy.data.source.remote.utlis.OnDataCallback

interface AreaDataSource {
    fun getArea(callback: OnDataCallback<List<Area>>)
}
