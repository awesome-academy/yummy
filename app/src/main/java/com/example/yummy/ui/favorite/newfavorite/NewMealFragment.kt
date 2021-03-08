package com.example.yummy.ui.favorite.newfavorite

import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.data.model.Meal
import com.example.yummy.ui.adapter.MealFavoriteAdapter
import com.example.yummy.ui.dialog.LoadingDialog
import com.example.yummy.ui.mealdetail.MealDetailFragment
import com.example.yummy.utlis.*
import kotlinx.android.synthetic.main.fragment_favorite_new.*

class NewMealFragment : BaseFragment(), NewMealContract.View {
    private val adapter = MealFavoriteAdapter(this::itemMealClicked)
    private var presenter: NewMealPresenter? = null
    private var loadingDialog: LoadingDialog? = null

    override val layoutResource get() = R.layout.fragment_favorite_new

    override fun setupViews() {
        initAdapter()
        initDialog()
    }

    override fun initData() {
        context?.let {
            val mealRepository = RepositoryUtils.getMealRepository(it)
            presenter = NewMealPresenter(
                this,
                mealRepository
            )
        }
        presenter?.start()
    }

    override fun initActions() {

    }

    override fun showNewMealFavorite(meals: List<Meal>) {
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
        recyclerNewFavorite.adapter = adapter
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
