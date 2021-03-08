package com.example.yummy.ui.home

import com.example.yummy.data.model.Category
import com.example.yummy.data.model.Ingredient
import com.example.yummy.data.repository.CategoryRepository
import com.example.yummy.data.repository.IngredientRepository
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import java.lang.Exception

class HomePresenter(
    private val view: HomeContract.View,
    private val categoryRepository: CategoryRepository,
    private val ingredientRepository: IngredientRepository
) : HomeContract.Presenter {

    override fun getCategories() {
        view.showLoading()
        categoryRepository.getCategories(object : OnDataCallback<List<Category>> {
            override fun onSuccess(data: List<Category>) {
                view.showCategories(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception?) {
                view.showError(exception?.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun getIngredients() {
        view.showLoading()
        ingredientRepository.getIngredients(object : OnDataCallback<List<Ingredient>> {
            override fun onSuccess(data: List<Ingredient>) {
                view.showIngredients(data)
                view.hideLoading()
            }

            override fun onFail(exception: Exception?) {
                view.showError(exception?.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun start() {
        getCategories()
        getIngredients()
    }
}
