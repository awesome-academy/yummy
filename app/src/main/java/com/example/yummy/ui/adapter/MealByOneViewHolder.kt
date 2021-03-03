package com.example.yummy.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.yummy.data.model.Meal
import com.example.yummy.utlis.loadImage
import kotlinx.android.synthetic.main.item_meal.view.*

class MealByOneViewHolder(
    itemView: View,
    onItemClicked: (Meal) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var itemMeal: Meal? = null

    init {
        itemView.setOnClickListener {
            itemMeal?.let(onItemClicked)
        }
    }

    fun onBind(meal: Meal) {
        itemMeal = meal
        itemView.apply {
            imageMeal.loadImage(meal.image)
            textMeal.text = meal.name
        }
    }
}
