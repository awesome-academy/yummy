package com.example.yummy.ui.favorite.newfavorite

import com.example.yummy.data.model.Meal
import com.example.yummy.data.repository.MealRepository
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.ui.favorite.allfavorite.AllMealContract
import com.example.yummy.ui.favorite.allfavorite.AllMealPresenter
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*
import java.lang.Exception

class NewMealPresenterTest {
    private val view = mockk<NewMealContract.View>(relaxed = true)
    private val repository = mockk<MealRepository>()
    private val presenter = NewMealPresenter(view, repository)
    private val callback = slot<OnDataLocalCallback<List<Meal>>>()
    private var exception: Exception? = null

    @Test
    fun `get New Meal return success`() {
        val meals = listOf<Meal>()
        every {
            repository.getNewMeals(capture(callback))
        } answers {
            callback.captured.onSuccess(meals)
        }

        presenter.getNewMealFavorite()
        verify {
            view.showLoading()
            view.showNewMealFavorite(meals)
            view.hideLoading()
        }
    }

    @Test
    fun `get New Meal return fair`() {
        exception = Exception()
        every {
            repository.getNewMeals(capture(callback))
        } answers {
            callback.captured.onFail(exception!!)
        }

        presenter.getNewMealFavorite()
        verify {
            view.showLoading()
            view.showError(exception!!.message.toString())
        }
    }
}
