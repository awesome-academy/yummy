package com.example.yummy.ui.search

import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.data.model.Area
import com.example.yummy.ui.adapter.AreaAdapter
import com.example.yummy.ui.dialog.LoadingDialog
import com.example.yummy.ui.meallist.MealListFragment
import com.example.yummy.utlis.*
import kotlinx.android.synthetic.main.fragment_area.*

class AreaFragment : BaseFragment(), AreaContract.View {

    private val areaAdapter = AreaAdapter()
    private var areaPresenter: AreaPresenter? = null
    private var loadingDialog: LoadingDialog? = null
    private var isConnection = false

    override val layoutResource get() = R.layout.fragment_area

    override fun setupViews() {
        initAdapter()
        initDialog()
    }

    override fun initData() {
        val repository = RepositoryUtils.getAreaRepository()
        areaPresenter = AreaPresenter(this, repository)
        val context = context ?: return
        isConnection = NetworkUtil.isConnection(context)
        if (!isConnection) {
            view?.showSnackBar(getString(R.string.msg_check_internet))
            return
        }
        areaPresenter?.start()
    }

    override fun initActions() {
        textSearch.setOnClickListener {
            parentFragmentManager.replaceFragment(R.id.frameMain, SearchFragment())
        }
    }

    override fun showAreas(areas: List<Area>) {
        areaAdapter.updateData(areas)
    }

    override fun showError(message: String) {
        context?.showToast(message)
    }

    override fun showLoading() {
        loadingDialog?.show()
    }

    override fun hideLoading() {
        loadingDialog?.hide()
    }

    private fun initAdapter() {
        recyclerArea.adapter = areaAdapter.apply {
            onItemClick = { item ->
                clickItem(item)
            }
        }
    }

    private fun initDialog() {
        context?.let { loadingDialog = LoadingDialog(it) }
    }

    private fun clickItem(area: Area) {
        parentFragmentManager.replaceFragment(
            R.id.frameMain,
            MealListFragment.getInstance(area)
        )
    }
}
