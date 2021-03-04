package com.example.yummy.ui.search

import com.example.yummy.base.BasePresenter
import com.example.yummy.base.BaseView
import com.example.yummy.data.model.Meal

interface SearchContract {
    interface View : BaseView {
        fun showMealsByWordSearch(meals: List<Meal>)
        fun showNotFound()
        fun hideNotFound()
    }

    interface Presenter : BasePresenter {
        fun getMealsByWordSearch(wordSearch: String)
    }
}
