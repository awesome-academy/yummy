package com.example.yummy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yummy.R
import com.example.yummy.data.model.Meal

class MealByOneAdapter(private val onItemClicked: (Meal) -> Unit) :
    RecyclerView.Adapter<MealByOneViewHolder>() {

    private val meals = mutableListOf<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealByOneViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return MealByOneViewHolder(view, onItemClicked)
    }

    override fun getItemCount() = meals.size

    override fun onBindViewHolder(holder: MealByOneViewHolder, position: Int) {
        holder.onBind(meals[position])
    }

    fun updateData(newMeals: List<Meal>) {
        meals.run {
            clear()
            addAll(newMeals)
            notifyDataSetChanged()
        }
    }
}
