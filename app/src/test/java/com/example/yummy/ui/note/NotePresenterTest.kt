package com.example.yummy.ui.note

import com.example.yummy.R
import com.example.yummy.data.model.Note
import com.example.yummy.data.repository.NoteRepository
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import io.mockk.*
import org.junit.Test

class NotePresenterTest {

    private val view = mockk<NoteContract.View>(relaxed = true)
    private val repository = mockk<NoteRepository>()
    private val notePresenter = NotePresenter(view, repository)
    private val callback = slot<OnDataLocalCallback<Boolean>>()
    private val callbackGetAll = slot<OnDataLocalCallback<List<Note>>>()

    @Test
    fun `insertNote callback return onSuccess`() {
        val note = Note()
        val status = true
        every {
            repository.addNote(note, capture(callback))
        } answers {
            callback.captured.onSuccess(status)
        }
        notePresenter.insertNote(note)
        verify {
            view.showMessage(R.string.msg_insert_success)
            view.closeFragment()
        }
    }

    @Test
    fun `insertNote callback return onFail`() {
        val exception = Exception()
        val note = Note()
        every {
            repository.addNote(note, capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }
        notePresenter.insertNote(note)
        verify {
            view.showError(exception.message.toString())
        }
    }

    @Test
    fun `updateNote callback return onSuccess`() {
        val note = Note()
        val status = true
        every {
            repository.updateNote(note, capture(callback))
        } answers {
            callback.captured.onSuccess(status)
        }
        notePresenter.updateNote(note)
        verify {
            view.showMessage(R.string.msg_update_success)
            view.closeFragment()
        }
    }

    @Test
    fun `updateNote callback return onFail`() {
        val exception = Exception()
        val note = Note()
        every {
            repository.updateNote(note, capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }
        notePresenter.updateNote(note)
        verify {
            view.showError(exception.message.toString())
        }
    }

    @Test
    fun `deleteNote callback return onSuccess, getAllNote callback return onSuccess`() {
        val idNote = 1
        val status = true
        val notes = mutableListOf<Note>()
        every {
            repository.deleteNote(idNote, capture(callback))
        } answers {
            callback.captured.onSuccess(status)
        }
        every {
            repository.getAllNotes(capture(callbackGetAll))
        } answers {
            callbackGetAll.captured.onSuccess(notes)
        }
        notePresenter.deleteNote(idNote)
        verify {
            view.showMessage(R.string.msg_delete_success)
            view.showLoading()
            view.hideLoading()
            view.showAllNotes(notes)
        }
    }

    @Test
    fun `deleteNote callback return onSuccess, getAllNote callback return onFail`() {
        val idNote = 1
        val status = true
        val exception = Exception()
        every {
            repository.deleteNote(idNote, capture(callback))
        } answers {
            callback.captured.onSuccess(status)
        }
        every {
            repository.getAllNotes(capture(callbackGetAll))
        } answers {
            callbackGetAll.captured.onFail(exception)
        }
        notePresenter.deleteNote(idNote)
        verify {
            view.showMessage(R.string.msg_delete_success)
            view.showLoading()
            view.hideLoading()
            view.showError(exception.message.toString())
        }
    }

    @Test
    fun `deleteNote callback return onFail`() {
        val exception = Exception()
        val idNote = 1
        every {
            repository.deleteNote(idNote, capture(callback))
        } answers {
            callback.captured.onFail(exception)
        }
        notePresenter.deleteNote(idNote)
        verify {
            view.showError(exception.message.toString())
        }
    }

    @Test
    fun `getAllNotes callback return onSuccess`() {
        val notes = mutableListOf<Note>()
        every {
            repository.getAllNotes(capture(callbackGetAll))
        } answers {
            callbackGetAll.captured.onSuccess(notes)
        }
        notePresenter.getAllNotes()
        verify {
            view.showLoading()
            view.hideLoading()
            view.showAllNotes(notes)
        }
    }

    @Test
    fun `getAllNotes callback return onFail`() {
        val exception = Exception()
        every {
            repository.getAllNotes(capture(callbackGetAll))
        } answers {
            callbackGetAll.captured.onFail(exception)
        }
        notePresenter.getAllNotes()
        verify {
            view.showLoading()
            view.hideLoading()
            view.showError(exception.message.toString())
        }
    }
}
