package com.example.yummy.data.source

import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.data.model.Note

interface NoteDataSource {
    fun getAllNotes(callback: OnDataLocalCallback<List<Note>>)
    fun addNote(note: Note, callback: OnDataLocalCallback<Boolean>)
    fun updateNote(note: Note, callback: OnDataLocalCallback<Boolean>)
    fun deleteNote(id: Int, callback: OnDataLocalCallback<Boolean>)
}
