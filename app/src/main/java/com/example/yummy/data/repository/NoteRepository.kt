package com.example.yummy.data.repository

import com.example.yummy.data.source.NoteDataSource
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.data.model.Note

class NoteRepository private constructor(
    private val local: NoteDataSource
) : NoteDataSource {

    override fun getAllNotes(callback: OnDataLocalCallback<List<Note>>) {
        local.getAllNotes(callback)
    }

    override fun addNote(note: Note, callback: OnDataLocalCallback<Boolean>) {
        local.addNote(note, callback)
    }

    override fun updateNote(note: Note, callback: OnDataLocalCallback<Boolean>) {
        local.updateNote(note, callback)
    }

    override fun deleteNote(id: Int, callback: OnDataLocalCallback<Boolean>) {
        local.deleteNote(id, callback)
    }

    companion object {
        private var instance: NoteRepository? = null
        fun getInstance(local: NoteDataSource) =
            instance ?: NoteRepository(local).also { instance = it }
    }
}
