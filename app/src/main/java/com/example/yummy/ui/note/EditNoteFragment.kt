package com.example.yummy.ui.note

import androidx.core.os.bundleOf
import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.data.model.Note
import com.example.yummy.utlis.RepositoryUtils
import com.example.yummy.utlis.removeFragment
import com.example.yummy.utlis.showToast
import kotlinx.android.synthetic.main.fragment_add_note.*

class EditNoteFragment : BaseFragment(), NoteContract.View {

    private var notePresenter: NotePresenter? = null
    private var note: Note? = null

    override val layoutResource get() = R.layout.fragment_add_note

    override fun setupViews() {
        textTitle.text = getString(R.string.title_update_note)
        arguments?.let {
            note = it.getParcelable(BUNDLE_NOTE)
            editTextName.setText(note?.title)
            editTextDescription.setText(note?.description)
        }
    }

    override fun initData() {
        val context = context ?: return
        val repository = RepositoryUtils.getNoteRepository(context)
        notePresenter = NotePresenter(this, repository)
    }

    override fun initActions() {
        imageButtonCancel.setOnClickListener {
            closeFragment()
        }
        imageButtonOk.setOnClickListener {
            when {
                editTextName.text.isEmpty() -> {
                    editTextName.error = getString(R.string.error_not_empty)
                }
                editTextName.text.toString() != note?.title || editTextDescription.text.toString() != note?.description -> {
                    note?.apply {
                        title = editTextName.text.toString()
                        description = editTextDescription.text.toString()
                        notePresenter?.updateNote(this)
                    }
                }
                else -> closeFragment()
            }
        }
    }

    override fun showAllNotes(notes: List<Note>) {
    }

    override fun showMessage(message: Int) {
        context?.showToast(getString(message))
    }

    override fun closeFragment() {
        parentFragmentManager.removeFragment(R.id.frameMain, this)
    }

    override fun showError(message: String) {
        context?.showToast(message)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    companion object {
        private const val BUNDLE_NOTE = "BUNDLE_NOTE"
        fun newInstanceBundle(note: Note) = EditNoteFragment()
            .apply {
                arguments = bundleOf(BUNDLE_NOTE to note)
            }
    }
}
