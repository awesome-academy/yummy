package com.example.yummy.ui.note

import com.example.yummy.base.BasePresenter
import com.example.yummy.base.BaseView
import com.example.yummy.data.model.Note

interface NoteContract {
    interface View : BaseView {
        fun showAllNotes(notes: List<Note>)
        fun showMessage(message: Int)
        fun closeFragment()
    }

    interface Presenter : BasePresenter {
        fun insertNote(note: Note)
        fun updateNote(note: Note)
        fun deleteNote(idNote: Int)
        fun getAllNotes()
    }
}
