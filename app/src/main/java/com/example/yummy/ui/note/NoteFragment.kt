package com.example.yummy.ui.note

import com.example.yummy.R
import com.example.yummy.base.BaseFragment
import com.example.yummy.data.model.Note
import com.example.yummy.ui.adapter.NoteAdapter
import com.example.yummy.ui.dialog.LoadingDialog
import com.example.yummy.utlis.RepositoryUtils
import com.example.yummy.utlis.replaceFragment
import com.example.yummy.utlis.showToast
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : BaseFragment(),
    NoteContract.View,
    NoteAdapter.OnItemClickListener {

    private val noteAdapter = NoteAdapter(this)
    private var notePresenter: NoteContract.Presenter? = null
    private var loadingDialog: LoadingDialog? = null

    override val layoutResource get() = R.layout.fragment_note

    override fun setupViews() {
        initDialog()
        initAdapter()
    }

    override fun initData() {
        val context = context ?: return
        val repository = RepositoryUtils.getNoteRepository(context)
        notePresenter = NotePresenter(this, repository)
        notePresenter?.start()
    }

    override fun initActions() {
        floatButtonAdd.setOnClickListener {
        }
    }

    override fun showAllNotes(notes: List<Note>) {
        noteAdapter.updateData(notes)
    }

    override fun showMessage(message: Int) {
        context?.showToast(getString(message))
    }

    override fun showError(message: String) {
        context?.showToast(message)
    }

    override fun showLoading() {
        loadingDialog?.show()
    }

    override fun hideLoading() {
        loadingDialog?.hide()
    }

    override fun openContentNoteFragment(note: Note) =
        parentFragmentManager.replaceFragment(R.id.frameMain, NoteContentFragment.newInstance(note))

    override fun openDeleteNoteDialog(note: Note) =
        DialogDeleteFragment.newInstance(note.id, ::chooseYes).show(parentFragmentManager, null)

    override fun openEditNoteFragment(note: Note) {
    }

    private fun initDialog() {
        context?.let { loadingDialog = LoadingDialog(it) }
    }

    private fun initAdapter() {
        recyclerNote.adapter = noteAdapter
    }

    private fun chooseYes(idNote: Int) {
        notePresenter?.deleteNote(idNote)
    }
}
