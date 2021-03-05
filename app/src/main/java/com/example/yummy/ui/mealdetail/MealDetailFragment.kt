package com.example.yummy.ui.mealdetail

import android.view.View
import androidx.core.os.bundleOf
import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.data.model.Meal
import com.example.yummy.data.model.MealDetail
import com.example.yummy.ui.dialog.LoadingDialog
import com.example.yummy.utlis.*
import kotlinx.android.synthetic.main.fragment_meal_detail.*

class MealDetailFragment : BaseFragment(), MealDetailContract.View {
    private var presenter: MealDetailPresenter? = null
    private var loadingDialog: LoadingDialog? = null
    private var meal: Meal? = null

    override val layoutResource: Int
        get() = R.layout.fragment_meal_detail

    override fun onStop() {
        super.onStop()
        when (buttonFavorite.visibility) {
            View.VISIBLE -> meal?.let { presenter?.insertMealFavorite(it) }
            else -> meal?.let { presenter?.deleteMealFavorite(it.id) }
        }
    }

    override fun setupViews() {
        initDialog()
    }

    override fun initData() {
        initInputData()
        context?.let {
            val mealRepository = RepositoryUtils.getMealRepository(it)
            presenter = MealDetailPresenter(this, mealRepository)
        }
        presenter?.start()
        bindMealData()
    }

    private fun bindMealData() {
        meal?.let { meal ->
            presenter?.apply {
                getMealDetailByMeal(meal)
                getMealFavorite(meal)
            }
        }
    }

    override fun initActions() {
        buttonBackDetail.apply {
            increaseHitArea(DIMENSION_20)
            setOnClickListener {
                parentFragmentManager.removeFragment(
                    R.id.frameMain,
                    this@MealDetailFragment
                )
            }
        }

        imageMealDetail.setOnClickListener {
            meal?.let { meal ->
                parentFragmentManager.replaceFragment(
                    R.id.frameMain,
                    ZoomImageFragment.newInstance(meal.image)
                )
            }
        }

        buttonFavorite.setOnClickListener {
            it.increaseHitArea(DIMENSION_20)
            val animFavorite = context?.getAnimation(R.anim.scale_favorite_up)
            it.visibility = View.GONE
            it.startAnimation(animFavorite)
            buttonUnFavorite.visibility = View.VISIBLE
        }

        buttonUnFavorite.setOnClickListener {
            it.increaseHitArea(DIMENSION_20)
            val animFavorite = context?.getAnimation(R.anim.scale_favorite_up)
            it.visibility = View.GONE
            it.startAnimation(animFavorite)
            buttonFavorite.visibility = View.VISIBLE
        }
    }

    override fun showMealDetailByMeal(mealDetails: List<MealDetail>) {
        mealDetails.first {
            textAreaDetail.text = it.area
            textCategoryDetail.text = it.category
            textIngredientDetail.text = it.Ingredients
            true
        }
    }

    override fun showMealFavorite(isFavorite: Int) {
        when {
            isFavorite >= 1 -> {
                buttonFavorite.visibility = View.VISIBLE
            }
            else -> buttonUnFavorite.visibility = View.VISIBLE
        }
    }

    override fun isInsertedMealFavorite(long: Long) {
        if (long > 0) context?.showToast(
            String.format(
                resources.getString(R.string.msg_insert_success_meal_favorite),
                meal?.name
            )
        )
    }

    override fun isDeletedMealFavorite(boolean: Boolean) {
        if (boolean) context?.showToast(
            String.format(
                resources.getString(R.string.msg_delete_success_meal_favorite),
                meal?.name
            )
        )
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
        context?.let {
            loadingDialog = LoadingDialog(it)
        }
    }

    private fun initInputData() {
        meal = arguments?.getParcelable<Meal>(BUNDLE_MEAL_DETAIL)?.apply {
            imageMealDetail.loadImageCircle(this.image)
            textMealName.text = this.name
        }
    }

    companion object {
        private const val BUNDLE_MEAL_DETAIL = "BUNDLE_MEAL_DETAIL"
        const val DIMENSION_20 = 20f

        fun getInstance(meal: Meal) = MealDetailFragment().apply {
            arguments = bundleOf(BUNDLE_MEAL_DETAIL to meal)
        }
    }
}
