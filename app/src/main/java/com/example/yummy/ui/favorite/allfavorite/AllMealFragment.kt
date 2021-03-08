package com.example.yummy.ui.favorite.allfavorite

import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.data.model.Meal
import com.example.yummy.ui.adapter.MealFavoriteAdapter
import com.example.yummy.ui.dialog.LoadingDialog
import com.example.yummy.ui.mealdetail.MealDetailFragment
import com.example.yummy.utlis.RepositoryUtils
import com.example.yummy.utlis.replaceFragment
import com.example.yummy.utlis.showToast
import kotlinx.android.synthetic.main.fragment_favorite_all.*

class AllMealFragment : BaseFragment(), AllMealContract.View {
    private val adapter = MealFavoriteAdapter(this::itemMealClicked)
    private var presenter: AllMealPresenter? = null
    private var loadingDialog: LoadingDialog? = null

    override val layoutResource get() = R.layout.fragment_favorite_all

    override fun setupViews() {
        initAdapter()
        initDialog()
    }

    override fun initData() {
        context?.let {
            val mealRepository = RepositoryUtils.getMealRepository(it)
            presenter = AllMealPresenter(
                this,
                mealRepository
            )
        }
        presenter?.start()
    }

    override fun initActions() {

    }

    override fun showAllMealFavorite(meals: List<Meal>) {
        adapter.updateData(meals)
    }

    override fun showError(message: String) {
        context?.showToast(message)
    }

    override fun showLoading() {
        loadingDialog?.show()
    }

    override fun hideLoading() {
        loadingDialog?.dismiss()
    }

    private fun initDialog() {
        context?.let { loadingDialog = LoadingDialog(it) }
    }

    private fun initAdapter() {
        recyclerFavoriteAll.adapter = adapter
    }

    private fun itemMealClicked(meal: Meal) {
        parentFragment?.apply {
            parentFragmentManager.replaceFragment(
                R.id.frameMain,
                MealDetailFragment.getInstance(meal)
            )
        }
    }
}
