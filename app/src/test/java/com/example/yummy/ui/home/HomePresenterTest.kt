package com.example.yummy.ui.home

import com.example.yummy.data.model.Category
import com.example.yummy.data.model.Ingredient
import com.example.yummy.data.repository.CategoryRepository
import com.example.yummy.data.repository.IngredientRepository

import com.example.yummy.data.source.remote.utlis.OnDataCallback
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

import java.lang.Exception

class HomePresenterTest {
    private val view = mockk<HomeContract.View>(relaxed = true)
    private val categoryRepository = mockk<CategoryRepository>()
    private val ingredientRepository = mockk<IngredientRepository>()
    private val presenter = HomePresenter(view, categoryRepository, ingredientRepository)
    private val callbackCategory = slot<OnDataCallback<List<Category>>>()
    private val callbackIngredient = slot<OnDataCallback<List<Ingredient>>>()
    private var exception: Exception? = null

    @Test
    fun `get Categories`() {
        val categories = listOf<Category>()
        every {
            categoryRepository.getCategories(capture(callbackCategory))
        } answers {
            callbackCategory.captured.onSuccess(categories)
        }

        presenter.getCategories()
        verify {
            view.showLoading()
            view.showCategories(categories)
            view.hideLoading()
        }
    }

    @Test
    fun `get Categories return fair, is not null`() {
        exception = Exception()
        every {
            categoryRepository.getCategories(capture(callbackCategory))
        } answers {
            callbackCategory.captured.onFail(exception)
        }

        presenter.getCategories()
        verify {
            view.showLoading()
            view.showError(exception!!.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `get Categories return fair, is null`() {
        every {
            categoryRepository.getCategories(capture(callbackCategory))
        } answers {
            callbackCategory.captured.onFail(exception)
        }

        presenter.getCategories()
        verify {
            view.showLoading()
            view.showError(exception.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `get Ingredient`() {
        val ingredients = listOf<Ingredient>()
        every {
            ingredientRepository.getIngredients(capture(callbackIngredient))
        } answers {
            callbackIngredient.captured.onSuccess(ingredients)
        }

        presenter.getIngredients()
        verify {
            view.showLoading()
            view.showIngredients(ingredients)
            view.hideLoading()
        }
    }

    @Test
    fun `get Ingredient return fair, is not null`() {
        exception = Exception()
        every {
            ingredientRepository.getIngredients(capture(callbackIngredient))
        } answers {
            callbackIngredient.captured.onFail(exception)
        }

        presenter.getIngredients()
        verify {
            view.showLoading()
            view.showError(exception!!.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `get Ingredients return fair, is null`() {
        every {
            ingredientRepository.getIngredients(capture(callbackIngredient))
        } answers {
            callbackIngredient.captured.onFail(exception)
        }

        presenter.getIngredients()
        verify {
            view.showLoading()
            view.showError(exception.toString())
            view.hideLoading()
        }
    }
}
