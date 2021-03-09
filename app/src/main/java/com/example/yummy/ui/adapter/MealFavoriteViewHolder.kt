package com.example.yummy.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.yummy.data.model.Meal
import com.example.yummy.utlis.loadImageCircle
import kotlinx.android.synthetic.main.item_favorite_meal.view.*
import java.util.*

class MealFavoriteViewHolder(
    itemView: View,
    onItemClicked: (Meal) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var itemMeal: Meal? = null

    init {
        itemView.setOnClickListener {
            itemMeal?.let(onItemClicked)
        }
    }

    fun onBind(meal: Meal, postMeal: Meal?) {
        itemMeal = meal
        itemView.apply {
            itemMeal?.let {
                if (it.name.substring(0, 1) != postMeal?.name?.substring(0, 1)) {
                    textSort.visibility = View.VISIBLE
                    textSort.text = meal.name.toUpperCase(Locale.ROOT).substring(0, 1)
                }
            }
            imageMeal.loadImageCircle(meal.image)
            textTitleMeal.text = meal.name
        }
    }
}
