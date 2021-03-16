package com.example.yummy.ui.search

import com.example.yummy.data.model.Meal
import com.example.yummy.data.repository.MealRepository
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import io.mockk.*
import org.junit.Test

class SearchPresenterTest {

    private val view = mockk<SearchContract.View>(relaxed = true)
    private val repository = mockk<MealRepository>()
    private val searchPresenter = SearchPresenter(view, repository)
    private val callback = slot<OnDataCallback<List<Meal>>>()

    @Test
    fun `getMealsByWordSearch callback return onSuccess, list result is empty`() {
        val meals = mutableListOf<Meal>()
        every {
            repository.searchMeal("", capture(callback))
        } answers {
            callback.captured.onSuccess(meals)
        }
        searchPresenter.getMealsByWordSearch("")
        verify {
            view.showLoading()
            view.showNotFound()
            view.hideLoading()
            view.showMealsByWordSearch(meals)
        }
    }

    @Test
    fun `getMealsByWordSearch callback return onSuccess, list result is not empty`() {
        val meals = mutableListOf<Meal>()
        meals.add(Meal("", "", "", 123))
        every {
            repository.searchMeal("", capture(callback))
        } answers {
            callback.captured.onSuccess(meals)
        }
        searchPresenter.getMealsByWordSearch("")
        verify {
            view.showLoading()
            view.hideNotFound()
            view.hideLoading()
            view.showMealsByWordSearch(meals)
        }
    }

    @Test
    fun `getMealsByWordSearch callback return onFail, exception is not null`() {
        val exception = Exception()
        every {
            repository.searchMeal("", capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }
        searchPresenter.getMealsByWordSearch("")
        verify {
            view.showLoading()
            view.showError(exception.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `getMealsByWordSearch callback return onFail, exception is null`() {
        val exception: Exception? = null
        every {
            repository.searchMeal("", capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }
        searchPresenter.getMealsByWordSearch("")
        verify {
            view.showLoading()
            view.showError(exception?.message.toString())
            view.hideLoading()
        }
    }
}
