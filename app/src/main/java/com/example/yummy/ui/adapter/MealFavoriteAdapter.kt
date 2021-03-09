package com.example.yummy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yummy.R
import com.example.yummy.data.model.Meal

class MealFavoriteAdapter(private val onItemClicked: (Meal) -> Unit) :
    RecyclerView.Adapter<MealFavoriteViewHolder>() {

    private val allMeals = mutableListOf<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealFavoriteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_meal, parent, false)
        return MealFavoriteViewHolder(view, onItemClicked)
    }

    override fun getItemCount() = allMeals.size

    override fun onBindViewHolder(holder: MealFavoriteViewHolder, position: Int) {
        if (position == 0) {
            holder.onBind(allMeals[position], null)
        } else
            holder.onBind(allMeals[position], allMeals[position - 1])
    }

    fun updateData(newMeals: List<Meal>) {
        allMeals.run {
            clear()
            addAll(newMeals)
            notifyDataSetChanged()
        }
    }
}
