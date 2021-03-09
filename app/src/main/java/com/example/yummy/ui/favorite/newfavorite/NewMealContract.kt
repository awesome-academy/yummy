package com.example.yummy.ui.favorite.newfavorite

import com.example.yummy.base.BasePresenter
import com.example.yummy.base.BaseView
import com.example.yummy.data.model.Meal

interface NewMealContract {
    interface View : BaseView {
        fun showNewMealFavorite(meals: List<Meal>)
    }

    interface Presenter : BasePresenter {
        fun getNewMealFavorite()
    }
}
