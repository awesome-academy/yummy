package com.example.yummy.ui.search

import com.example.yummy.data.model.Area
import com.example.yummy.data.repository.AreaRepository
import com.example.yummy.data.source.remote.utlis.OnDataCallback
import io.mockk.*
import org.junit.Test
import java.lang.Exception

class AreaPresenterTest {

    private val view = mockk<AreaContract.View>(relaxed = true)
    private val repository = mockk<AreaRepository>()
    private var areaPresenter = AreaPresenter(view, repository)
    private val callback = slot<OnDataCallback<List<Area>>>()

    @Test
    fun `getAreas callback return onSuccess`() {
        val areas = mutableListOf<Area>()
        every {
            repository.getArea(capture(callback))
        } answers {
            callback.captured.onSuccess(areas)
        }
        areaPresenter.getAreas()
        verify {
            view.showLoading()
            view.hideLoading()
            view.showAreas(areas)
        }
    }

    @Test
    fun `getAreas callback return onFail`() {
        val exception = Exception()
        every {
            repository.getArea(capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }
        areaPresenter.getAreas()
        verify {
            view.showLoading()
            view.hideLoading()
        }
    }
}
