package com.example.yummy.ui.adapter

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.yummy.data.model.Area
import kotlinx.android.synthetic.main.item_area.view.*
import java.util.*

class AreaViewHolder(
    itemView: View,
    private var clickItem: (Area) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var itemArea: Area? = null
    private val rnd = Random()
    private val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

    init {
        itemView.setOnClickListener {
            itemArea?.let {
                clickItem(it)
            }
        }
    }

    fun bindData(area: Area) {
        itemArea = area
        itemView.apply {
            textArea.setBackgroundColor(color)
            textArea.text = area.name
        }
    }
}
