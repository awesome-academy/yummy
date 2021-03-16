package com.example.yummy.ui.mealdetail

import com.example.yummy.data.model.Meal
import com.example.yummy.data.model.MealDetail
import com.example.yummy.data.repository.MealRepository
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import io.mockk.*
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class MealDetailPresenterTest {
    private val meal = mockk<Meal>(relaxed = true)
    private val view: MealDetailContract.View = mockk(relaxed = true)
    private val repository = mockk<MealRepository>()
    private lateinit var presenter: MealDetailPresenter
    private var callbackDetailMeal = slot<OnDataCallback<List<MealDetail>>>()
    private var callbackFavorite = slot<OnDataLocalCallback<Int>>()
    private var callbackInsert = slot<OnDataLocalCallback<Long>>()
    private var callbackDelete = slot<OnDataLocalCallback<Boolean>>()
    private var exception: Exception? = null

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = MealDetailPresenter(view, repository)
    }

    @Test
    fun `get meal detail return success`() {
        val meals = mutableListOf<MealDetail>()
        every {
            repository.getMealDetailByMeal(meal.name, capture(callbackDetailMeal))
        } answers {
            callbackDetailMeal.captured.onSuccess(meals)
        }
        presenter.getMealDetailByMeal(meal)
        verify {
            view.apply {
                showLoading()
                showMealDetailByMeal(meals)
                hideLoading()
            }
        }
    }

    @Test
    fun `get meal detail return false , is not null`() {
        exception = Exception()
        every { repository.getMealDetailByMeal(meal.name, capture(callbackDetailMeal)) } answers {
            callbackDetailMeal.captured.onFail(exception)
        }
        presenter.getMealDetailByMeal(meal)

        verify {
            view.showLoading()
            view.showError(exception?.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `get meal detail return false , is null`() {
        every { repository.getMealDetailByMeal(meal.name, capture(callbackDetailMeal)) } answers {
            callbackDetailMeal.captured.onFail(exception)
        }
        presenter.getMealDetailByMeal(meal)

        verify {
            view.showLoading()
            view.showError(exception?.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `get meal favorite return success`() {
        every {
            repository.isFavorite(meal.id, capture(callbackFavorite))
        } answers {
            callbackFavorite.captured.onSuccess(1)
        }

        presenter.getMealFavorite(meal)
        verify {
            view.showLoading()
            view.showMealFavorite(1)
            view.hideLoading()
        }
    }

    @Test
    fun `get meal favorite return fair`() {
        exception = Exception()
        every {
            repository.isFavorite(meal.id, capture(callbackFavorite))
        } answers {
            callbackFavorite.captured.onFail(exception!!)
        }

        presenter.getMealFavorite(meal)
        verify {
            view.showLoading()
            view.showError(exception?.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `insert meal favorite return success`() {
        every {
            repository.insertMeal(meal, capture(callbackInsert))
        } answers {
            callbackInsert.captured.onSuccess(1)
        }

        presenter.insertMealFavorite(meal)
        verify {
            view.showLoading()
            view.isInsertedMealFavorite(1)
            view.hideLoading()
        }
    }

    @Test
    fun `insert meal favorite return fair`() {
        val exception = Exception()

        every {
            repository.insertMeal(meal, capture(callbackInsert))
        } answers {
            callbackInsert.captured.onFail(exception)
        }
        presenter.insertMealFavorite(meal)

        verify {
            view.showLoading()
            view.showError(exception.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `delete meal favorite success`() {

        every {
            repository.deleteMeal(meal.id, capture(callbackDelete))
        } answers {
            callbackDelete.captured.onSuccess(true)
        }
        presenter.deleteMealFavorite(meal.id)

        verify {
            view.showLoading()
            view.isDeletedMealFavorite(true)
            view.hideLoading()
        }
    }

    @Test
    fun `delete meal favorite`() {
        val exception = Exception()

        every {
            repository.deleteMeal(meal.id, capture(callbackDelete))
        } answers {
            callbackDelete.captured.onFail(exception)
        }
        presenter.deleteMealFavorite(meal.id)

        verify {
            view.showLoading()
            view.showError(exception.message.toString())
            view.hideLoading()
        }
    }
}
