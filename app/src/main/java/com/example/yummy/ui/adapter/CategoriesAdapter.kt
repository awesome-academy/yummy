package com.example.yummy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yummy.R
import com.example.yummy.data.model.Category

class CategoriesAdapter(private val onItemClicked: (Category) -> Unit) :
    RecyclerView.Adapter<CategoriesViewHolder>() {

    private val categories = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoriesViewHolder(view, onItemClicked)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.onBind(categories[position])
    }

    fun updateData(categoryList: List<Category>) {
        categories.run {
            clear()
            this.addAll(categoryList)
            notifyDataSetChanged()
        }
    }
}
