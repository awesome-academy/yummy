package com.example.yummy.ui.home

import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.data.model.Category
import com.example.yummy.data.model.Ingredient
import com.example.yummy.ui.adapter.CategoriesAdapter
import com.example.yummy.ui.adapter.IngredientsAdapter
import com.example.yummy.ui.dialog.LoadingDialog
import com.example.yummy.utlis.RepositoryUtils
import com.example.yummy.utlis.addFragment
import com.example.yummy.utlis.showToast
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), HomeContract.View {
    private val categoriesAdapter = CategoriesAdapter(this::itemCategoryClicked)
    private val ingredientsAdapter = IngredientsAdapter(this::itemIngredientClicked)
    private var presenter: HomePresenter? = null
    private var loadingDialog: LoadingDialog? = null

    override val layoutResource: Int
        get() = R.layout.fragment_home

    override fun setupViews() {
        initAdapters()
        initDialog()
    }

    override fun initData() {
        val categoryRepository = RepositoryUtils.getCategoryRepository()
        val ingredientRepository = RepositoryUtils.getIngredientRepository()
        presenter = HomePresenter(this, categoryRepository, ingredientRepository)
        presenter?.start()
    }

    override fun initActions() {

    }

    override fun showCategories(categories: List<Category>) {
        categoriesAdapter.updateData(categories)
    }

    override fun showIngredients(ingredients: List<Ingredient>) {
        ingredientsAdapter.updateData(ingredients)
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

    private fun initAdapters() {
        recyclerCategory.adapter = categoriesAdapter
        recyclerIngredient.adapter = ingredientsAdapter
    }

    private fun initDialog() {
        context?.let {
            loadingDialog = LoadingDialog(it)
        }
    }

    private fun itemCategoryClicked(category: Category) {
        parentFragmentManager.addFragment(R.id.frameMain, this)
    }

    private fun itemIngredientClicked(ingredient: Ingredient) {
        parentFragmentManager.addFragment(R.id.frameMain, this)
    }
}
