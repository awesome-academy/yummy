package com.example.yummy.ui.home

import com.example.yummy.base.BasePresenter
import com.example.yummy.base.BaseView
import com.example.yummy.data.model.Category
import com.example.yummy.data.model.Ingredient

interface HomeContract {
    interface View : BaseView{
        fun showCategories(categories: List<Category>)
        fun showIngredients(ingredients: List<Ingredient>)
    }

    interface Presenter : BasePresenter{
        fun getCategories()
        fun getIngredients()
    }
}
