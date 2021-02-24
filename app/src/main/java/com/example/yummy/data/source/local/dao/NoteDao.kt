package com.example.yummy.data.source.local.dao

import com.example.yummy.data.source.model.Note

interface NoteDao {
    fun getAllNote() : List<Note>
    fun addNote(note: Note) : Boolean
    fun updateNote(note: Note) : Boolean
    fun deleteNote(id: Int) : Boolean
}
