package com.example.yummy.ui.favorite.allfavorite

import com.example.yummy.data.model.Category
import com.example.yummy.data.model.Meal
import com.example.yummy.data.repository.MealRepository
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

import java.lang.Exception

class AllMealPresenterTest {
    private val view = mockk<AllMealContract.View>(relaxed = true)
    private val repository = mockk<MealRepository>()
    private val presenter = AllMealPresenter(view, repository)
    private val callback = slot<OnDataLocalCallback<List<Meal>>>()
    private var exception: Exception? = null

    @Test
    fun `get All Meal return success`() {
        val meals = listOf<Meal>()
        every {
            repository.getAllMeals(capture(callback))
        } answers {
            callback.captured.onSuccess(meals)
        }

        presenter.getAllMealFavorite()
        verify {
            view.showLoading()
            view.showAllMealFavorite(meals)
            view.hideLoading()
        }
    }

    @Test
    fun `get All Meal return fair`() {
        exception = Exception()
        every {
            repository.getAllMeals(capture(callback))
        } answers {
            callback.captured.onFail(exception!!)
        }

        presenter.getAllMealFavorite()
        verify {
            view.showLoading()
            view.showError(exception!!.message.toString())
        }
    }
}
