package com.example.yummy.ui.note

import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.data.model.Note
import com.example.yummy.utlis.RepositoryUtils
import com.example.yummy.utlis.removeFragment
import com.example.yummy.utlis.showToast
import kotlinx.android.synthetic.main.fragment_add_note.*

class AddNoteFragment : BaseFragment(), NoteContract.View {

    private var notePresenter: NotePresenter? = null
    override val layoutResource get() = R.layout.fragment_add_note

    override fun setupViews() {
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
            if (editTextName.text.isEmpty()) {
                editTextName.error = getString(R.string.error_not_empty)
            } else {
                notePresenter?.insertNote(
                    Note(
                        title = editTextName.text.toString(),
                        description = editTextDescription.text.toString()
                    )
                )
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
}
