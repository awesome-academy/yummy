package com.example.yummy.ui.note

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.example.yummy.R
import com.example.yummy.base.BaseDialogFragment

class DialogDeleteFragment(
    private val onClickListener: (Int) -> Unit
) : BaseDialogFragment() {

    private var noteId: Int? = null

    override val title get() = getString(R.string.title_notification)
    override val content get() = getString(R.string.title_notification_question)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            noteId = it.getInt(BUNDLE_NOTE)
        }
    }

    override fun clickYes() {
        noteId?.let { onClickListener(it) }
        dialog?.dismiss()
    }

    override fun clickNo() {
        dialog?.dismiss()
    }

    companion object {
        private const val BUNDLE_NOTE = "BUNDLE_NOTE"
        fun newInstance(idNote: Int, onClickListener: (Int) -> Unit) =
            DialogDeleteFragment(onClickListener).apply {
                arguments = bundleOf(BUNDLE_NOTE to idNote)
            }
    }
}
