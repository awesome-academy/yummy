package com.example.yummy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yummy.R
import com.example.yummy.data.model.Area

class AreaAdapter : RecyclerView.Adapter<AreaViewHolder>() {

    var onItemClick: (Area) -> Unit = { _ -> }
    private val areas = mutableListOf<Area>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_area, parent, false)
        return AreaViewHolder(view, onItemClick)
    }

    override fun getItemCount() = areas.size

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        holder.bindData(areas[position])
    }

    fun updateData(newList: List<Area>) {
        areas.run {
            clear()
            addAll(newList)
            notifyDataSetChanged()
        }
    }
}
