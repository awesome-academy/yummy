package com.example.yummy.ui.favorite.allfavorite

import com.example.yummy.base.BasePresenter
import com.example.yummy.base.BaseView
import com.example.yummy.data.model.Meal

interface AllMealContract {
    interface View : BaseView {
        fun showAllMealFavorite(meals: List<Meal>)
    }

    interface Presenter : BasePresenter {
        fun getAllMealFavorite()
    }
}
