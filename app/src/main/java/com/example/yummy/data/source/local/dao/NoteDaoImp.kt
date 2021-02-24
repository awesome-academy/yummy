package com.example.yummy.data.source.local.dao

import android.annotation.SuppressLint
import com.example.yummy.data.source.local.db.YummyDatabase
import com.example.yummy.data.source.model.Note

class NoteDaoImp private constructor(database: YummyDatabase) : NoteDao {
    private val writableDB = database.writableDatabase
    private val readableDB = database.readableDatabase

    @SuppressLint("Recycle")
    override fun getAllNote(): List<Note> {
        val notes = mutableListOf<Note>()
        val cursor = readableDB.query(
            Note.NOTE_TABLE,
            arrayOf(Note.NOTE_ID, Note.NOTE_TITLE, Note.NOTE_DESCRIPTION, Note.NOTE_DATE),
            null, null, null, null, "${Note.NOTE_DATE} DESC"
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
        writableDB.close()
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
        writableDB.close()
        return result > 0
    }

    companion object {
        private var instance: NoteDaoImp? = null

        fun getInstance(database: YummyDatabase):
                NoteDaoImp =
            instance ?: synchronized(this) {
                instance ?: NoteDaoImp(database).also {
                    instance = it
                }
            }
    }
}
