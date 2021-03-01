package com.example.yummy.data.source.remote

import com.example.yummy.data.model.Area
import com.example.yummy.data.source.AreaDataSource
import com.example.yummy.data.source.remote.api.APIQuery
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import com.example.yummy.data.source.remote.utlis.RemoteAsyncTask
import com.example.yummy.data.source.remote.utlis.readContentApi
import com.example.yummy.utlis.NameConst.MEALS
import com.example.yummy.utlis.parseJsonArray
import org.json.JSONObject

@Suppress("DEPRECATION")
class AreaRemoteDataSource private constructor() : AreaDataSource {

    override fun getArea(callback: OnDataCallback<List<Area>>) {
        RemoteAsyncTask(callback) {
            getArea()
        }.execute()
    }

    private fun getArea(): List<Area> =
        JSONObject(readContentApi(APIQuery.queryArea())).getString(MEALS).parseJsonArray()

    companion object {
        private var instance: AreaRemoteDataSource? = null
        fun getInstance() =
            instance ?: AreaRemoteDataSource().also { instance = it }
    }
}
