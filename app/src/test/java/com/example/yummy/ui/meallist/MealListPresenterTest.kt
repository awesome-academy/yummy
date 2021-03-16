package com.example.yummy.ui.meallist

import com.example.yummy.data.model.Area
import com.example.yummy.data.model.Category
import com.example.yummy.data.model.Ingredient
import com.example.yummy.data.model.Meal
import com.example.yummy.data.repository.MealRepository
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*
import java.lang.Exception

class MealListPresenterTest {
    private val view = mockk<MealListContract.View>(relaxed = true)
    private val repository = mockk<MealRepository>()
    private val presenter = MealListPresenter(view, repository)
    private val callback = slot<OnDataCallback<List<Meal>>>()
    private var exception: Exception? = null
    private val meal = mockk<Meal>(relaxed = true)
    private val category = Category("1", "tomato", "tomato.jpg", "updating")
    private val ingredient = Ingredient("1", "tomato", "tomato.jpg", "updating")
    private val area = Area("1")

    @Test
    fun `get Meals By Category`() {
        val meals = listOf<Meal>()
        every {
            repository.getMealByCategory(category.name, capture(callback))
        } answers {
            callback.captured.onSuccess(meals)
        }

        presenter.getMealsByCategory(category)
        verify {
            view.showLoading()
            view.showMeals(meals)
            view.hideLoading()
        }
    }

    @Test
    fun `get Meals By Category return false , is not null`() {
        exception = Exception()
        every {
            repository.getMealByCategory(category.name, capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }

        presenter.getMealsByCategory(category)
        verify {
            view.showLoading()
            view.showError(exception!!.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `get Meals By Category return false , is null`() {
        every {
            repository.getMealByCategory(category.name, capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }

        presenter.getMealsByCategory(category)
        verify {
            view.showLoading()
            view.showError(exception?.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `get Meals By Ingredient`() {
        val meals = listOf<Meal>()
        every {
            repository.getMealByIngredient(ingredient.name, capture(callback))
        } answers {
            callback.captured.onSuccess(meals)
        }

        presenter.getMealsByIngredient(ingredient)
        verify {
            view.showLoading()
            view.showMeals(meals)
            view.hideLoading()
        }
    }

    @Test
    fun `get Meals By Ingredient return false , is not null`() {
        exception = Exception()
        every {
            repository.getMealByIngredient(ingredient.name, capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }

        presenter.getMealsByIngredient(ingredient)
        verify {
            view.showLoading()
            view.showError(exception!!.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `get Meals By Ingredient return false , is null`() {
        every {
            repository.getMealByIngredient(ingredient.name, capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }

        presenter.getMealsByIngredient(ingredient)
        verify {
            view.showLoading()
            view.showError(exception?.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `get Meals By Area`() {
        val meals = listOf<Meal>()
        every {
            repository.getMealByArea(area.name, capture(callback))
        } answers {
            callback.captured.onSuccess(meals)
        }

        presenter.getMealsByArea(area)
        verify {
            view.showLoading()
            view.showMeals(meals)
            view.hideLoading()
        }
    }

    @Test
    fun `get Meals By Area return false , is not null`() {
        exception = Exception()
        every {
            repository.getMealByArea(area.name, capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }

        presenter.getMealsByArea(area)
        verify {
            view.showLoading()
            view.showError(exception!!.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `get Meals By Area return false , is null`() {
        every {
            repository.getMealByArea(area.name, capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }

        presenter.getMealsByArea(area)
        verify {
            view.showLoading()
            view.showError(exception?.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun start() {
    }
}
