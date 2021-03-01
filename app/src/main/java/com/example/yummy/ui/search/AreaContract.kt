package com.example.yummy.ui.search

import com.example.yummy.base.BasePresenter
import com.example.yummy.base.BaseView
import com.example.yummy.data.model.Area

interface AreaContract {
    interface View : BaseView {
        fun showAreas(areas: List<Area>)
    }

    interface Presenter : BasePresenter {
        fun getAreas()
    }
}
