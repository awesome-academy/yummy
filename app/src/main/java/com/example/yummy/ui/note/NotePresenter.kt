package com.example.yummy.ui.note

import com.example.yummy.R
import com.example.yummy.data.model.Note
import com.example.yummy.data.repository.NoteRepository
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import java.lang.Exception

class NotePresenter(
    private val view: NoteContract.View,
    private val repository: NoteRepository
) : NoteContract.Presenter {

    override fun insertNote(note: Note) {
        repository.addNote(note, object : OnDataLocalCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.showMessage(R.string.msg_insert_success)
                view.closeFragment()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
            }
        })
    }

    override fun updateNote(note: Note) {
        repository.updateNote(note, object : OnDataLocalCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.showMessage(R.string.msg_update_success)
                view.closeFragment()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
            }
        })
    }

    override fun deleteNote(idNote: Int) {
        repository.deleteNote(idNote, object : OnDataLocalCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.showMessage(R.string.msg_delete_success)
                getAllNotes()
            }

            override fun onFail(exception: Exception) {
                view.showError(exception.message.toString())
            }
        })
    }

    override fun getAllNotes() {
        view.showLoading()
        repository.getAllNotes(object : OnDataLocalCallback<List<Note>> {
            override fun onSuccess(data: List<Note>) {
                view.hideLoading()
                view.showAllNotes(data)
            }

            override fun onFail(exception: Exception) {
                view.hideLoading()
                view.showError(exception.message.toString())
            }
        })
    }

    override fun start() {
        getAllNotes()
    }
}
