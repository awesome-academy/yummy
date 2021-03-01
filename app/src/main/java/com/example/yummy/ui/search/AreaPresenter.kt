package com.example.yummy.ui.search

import com.example.yummy.data.model.Area
import com.example.yummy.data.repository.AreaRepository
import com.example.yummy.data.source.remote.utlis.OnDataCallback

class AreaPresenter(
    private val view: AreaContract.View,
    private val repository: AreaRepository
) : AreaContract.Presenter {

    override fun getAreas() {
        view.showLoading()
        repository.getArea(object : OnDataCallback<List<Area>> {
            override fun onSuccess(data: List<Area>) {
                view.hideLoading()
                view.showAreas(data)
            }

            override fun onFail(exception: Exception?) {
                view.hideLoading()
            }
        })
    }

    override fun start() {
        getAreas()
    }
}
