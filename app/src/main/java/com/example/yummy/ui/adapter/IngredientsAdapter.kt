package com.example.yummy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yummy.R
import com.example.yummy.data.model.Ingredient

class IngredientsAdapter(val onItemClicked: (Ingredient) -> Unit) :
    RecyclerView.Adapter<IngredientsViewHolder>() {

    private val ingredients = mutableListOf<Ingredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return IngredientsViewHolder(view, onItemClicked)
    }

    override fun getItemCount() = ingredients.size

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.onBind(ingredients[position])
    }

    fun updateData(ingredientList: List<Ingredient>) {
        ingredients.run {
            clear()
            this.addAll(ingredientList)
            notifyDataSetChanged()
        }
    }
}
