package com.example.yummy.ui.search

import android.view.View
import androidx.core.widget.doOnTextChanged
import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.data.model.Meal
import com.example.yummy.ui.adapter.MealByOneAdapter
import com.example.yummy.ui.dialog.LoadingDialog
import com.example.yummy.ui.mealdetail.MealDetailFragment
import com.example.yummy.utlis.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment(), SearchContract.View {

    private val adapter = MealByOneAdapter(::clickItemMeal)
    private var searchPresenter: SearchPresenter? = null
    private var loadingDialog: LoadingDialog? = null
    private var isConnection = false

    override val layoutResource get() = R.layout.fragment_search

    override fun setupViews() {
        initDialog()
        initAdapter()
    }

    override fun initData() {
        val context = context ?: return
        val repository = RepositoryUtils.getMealRepository(context)
        searchPresenter = SearchPresenter(this, repository)
        isConnection = NetworkUtil.isConnection(context)
        if (!isConnection) {
            view?.showSnackBar(getString(R.string.msg_check_internet))
            return
        }
        searchPresenter?.start()
    }

    override fun initActions() {
        changeEditText()
        textCancel.setOnClickListener {
            parentFragmentManager.removeFragment(R.id.frameMain, this)
        }
        imageDelete.setOnClickListener {
            editSearch.text = null
            imageDelete.visibility = View.INVISIBLE
            textSuggest.text = getString(R.string.title_suggest)
        }
    }

    override fun showMealsByWordSearch(meals: List<Meal>) {
        adapter.updateData(meals)
    }

    override fun showNotFound() {
        imageNoResult.visibility = View.VISIBLE
    }

    override fun hideNotFound() {
        imageNoResult.visibility = View.INVISIBLE
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

    private fun initDialog() {
        context?.let { loadingDialog = LoadingDialog(it) }
    }

    private fun initAdapter() {
        recyclerSearch.adapter = adapter
    }

    private fun changeEditText() {
        editSearch.doOnTextChanged { text, _, _, _ ->
            text?.let {
                if (it.isEmpty()) {
                    textSuggest.text = getString(R.string.title_suggest)
                    imageDelete.visibility = View.INVISIBLE
                } else {
                    imageDelete.visibility = View.VISIBLE
                    textSuggest.text = getString(R.string.title_result)
                }
                searchPresenter?.getMealsByWordSearch(it.toString())
            }
        }
    }

    private fun clickItemMeal(meal: Meal) {
        parentFragmentManager.addFragment(R.id.frameMain, MealDetailFragment.getInstance(meal))
    }
}
