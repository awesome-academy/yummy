package com.example.yummy.data.source.local.dao

import android.annotation.SuppressLint
import com.example.yummy.data.source.local.db.AppDatabase
import com.example.yummy.data.model.Note

class NoteDaoImp private constructor(database: AppDatabase) : NoteDao {
    private val writableDB = database.writableDatabase
    private val readableDB = database.readableDatabase

    @SuppressLint("Recycle")
    override fun getAllNotes(): List<Note> {
        val notes = mutableListOf<Note>()
        val cursor = readableDB.query(
            Note.NOTE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "${Note.NOTE_DATE} DESC"
        )

        if (cursor.moveToFirst()) {
            do {
                val note = Note(cursor)
                notes.add(note)
            } while (cursor.moveToNext())
        }
        return notes
    }

    override fun addNote(note: Note): Boolean {
        val result = writableDB.insert(
            Note.NOTE_TABLE,
            null,
            note.getContentValue()
        )
        return result > 0
    }

    override fun updateNote(note: Note): Boolean {
        val result = writableDB.update(
            Note.NOTE_TABLE,
            note.getContentValue(),
            Note.NOTE_ID + "= " + note.id,
            null
        )
        return result > 0
    }

    override fun deleteNote(id: Int): Boolean {
        val result = writableDB.delete(
            Note.NOTE_TABLE,
            "${Note.NOTE_ID} =?",
            arrayOf(id.toString())
        )
        return result > 0
    }

    companion object {
        private var instance: NoteDaoImp? = null

        fun getInstance(database: AppDatabase): NoteDaoImp =
            instance ?: synchronized(this) {
                instance ?: NoteDaoImp(database).also {
                    instance = it
                }
            }
    }
}
