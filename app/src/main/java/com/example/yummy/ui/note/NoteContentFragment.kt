package com.example.yummy.ui.note

import androidx.core.os.bundleOf
import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.data.model.Note
import com.example.yummy.utlis.RepositoryUtils
import com.example.yummy.utlis.removeFragment
import com.example.yummy.utlis.showToast
import kotlinx.android.synthetic.main.fragment_note_content.*

class NoteContentFragment : BaseFragment(), NoteContract.View {

    private var notePresenter: NotePresenter? = null
    private var note: Note? = null

    override val layoutResource get() = R.layout.fragment_note_content

    override fun setupViews() {
        arguments?.let {
            note = it.getParcelable(BUNDLE_NOTE)
            editContent.setText(note?.description)
        }
    }

    override fun initData() {
        val context = context ?: return
        val repository = RepositoryUtils.getNoteRepository(context)
        notePresenter = NotePresenter(this, repository)
    }

    override fun initActions() {
        toolBarNote.setOnClickListener {
            if (editContent.text.toString() != note?.description) {
                note?.let {
                    it.description = editContent.text.toString()
                    notePresenter?.updateNote(it)
                }
            }
            parentFragmentManager.removeFragment(R.id.frameMain, this)
        }
    }

    override fun showAllNotes(notes: List<Note>) {
    }

    override fun showMessage(message: Int) {
        context?.showToast(getString(message))
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
        fun newInstance(note: Note) = NoteContentFragment().apply {
            arguments = bundleOf(BUNDLE_NOTE to note)
        }
    }
}
