package com.example.yummy.utlis

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.yummy.R

fun ImageView.loadImage(image: String) {
    Glide.with(context).load(image)
        .error(R.drawable.splash)
        .placeholder(R.drawable.placeholer)
        .into(this)
}
