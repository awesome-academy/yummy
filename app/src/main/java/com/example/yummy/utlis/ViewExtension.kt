package com.example.yummy.utlis

import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.TouchDelegate
import android.view.View
import com.example.yummy.R
import com.google.android.material.snackbar.Snackbar

fun View.increaseHitArea(dp: Float) {
    val increaseArea = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        Resources.getSystem().displayMetrics
    ).toInt()
    val parent = parent as View
    parent.post {
        val rect = Rect()
        getHitRect(rect)
        rect.apply {
            top -= increaseArea
            left -= increaseArea
            bottom += increaseArea
            right += increaseArea
        }
        parent.touchDelegate = TouchDelegate(rect, this)
    }
}

fun View.showSnackBar(message: String, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, length).setAnchorView(R.id.bottomNavigationView).show()
}
