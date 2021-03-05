package com.example.yummy.ui.mealdetail

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.utlis.removeFragment
import kotlinx.android.synthetic.main.fragment_image_zoom.*

class ZoomImageFragment : BaseFragment() {
    private var scaleGestureDetector: ScaleGestureDetector? = null
    private var scaleFactor = 1.0f

    override val layoutResource get() = R.layout.fragment_image_zoom

    @SuppressLint("ClickableViewAccessibility")
    override fun setupViews() {
        arguments?.let {
            val image = it.getString(IMAGE_ZOOM)
            context?.let { context -> Glide.with(context).load(image).into(imageZoom) }
        }
        scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
        view?.setOnTouchListener { _: View?, event: MotionEvent? ->
            scaleGestureDetector?.onTouchEvent(event)
            true
        }
    }

    override fun initData() {

    }

    override fun initActions() {
        imageCancel.setOnClickListener {
            parentFragmentManager.removeFragment(R.id.frameMain, this)
        }
    }

    companion object {
        const val DIMENSION_10 = 10f
        const val DIMENSION_01 = .1f
        const val IMAGE_ZOOM = "IMAGE_ZOOM"
        fun newInstance(strThumb: String) = ZoomImageFragment().apply {
            arguments = bundleOf(IMAGE_ZOOM to strThumb)
        }
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor =
                DIMENSION_01.coerceAtLeast(scaleFactor.coerceAtMost(DIMENSION_10))
            imageZoom.scaleX = scaleFactor
            imageZoom.scaleY = scaleFactor
            return true
        }
    }
}
