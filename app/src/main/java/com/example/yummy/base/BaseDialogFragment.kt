package com.example.yummy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.yummy.R
import kotlinx.android.synthetic.main.dialog_fragment.*

abstract class BaseDialogFragment : DialogFragment() {

    protected abstract val title: String
    protected abstract val content: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.let {
            it.setBackgroundDrawableResource(R.drawable.bg_white_radius_20)
            it.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return inflater.inflate(R.layout.dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textTitle.text = title
        textQuestion.text = content
        buttonYes.setOnClickListener {
            clickYes()
        }
        buttonNo.setOnClickListener {
            clickNo()
        }
    }

    protected abstract fun clickYes()

    protected abstract fun clickNo()
}
