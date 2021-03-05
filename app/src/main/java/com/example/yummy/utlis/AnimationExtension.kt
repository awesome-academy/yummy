package com.example.yummy.utlis

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes

fun Context.getAnimation(@AnimRes animLay: Int): Animation =
    AnimationUtils.loadAnimation(this, animLay)
