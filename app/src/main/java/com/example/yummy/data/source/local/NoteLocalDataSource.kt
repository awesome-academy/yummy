package com.example.yummy.data.source.local

import com.example.yummy.data.source.NoteDataSource
import com.example.yummy.data.source.local.dao.NoteDao
import com.example.yummy.data.source.local.utils.LocalAsyncTask
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.data.model.Note

class NoteLocalDataSource(
    private val noteDao: NoteDao
) : NoteDataSource{

    override fun getAllNotes(callback: OnDataLocalCallback<List<Note>>) {
        LocalAsyncTask<Unit, List<Note>>(callback) {
            noteDao.getAllNotes()
        }.execute()
    }

    override fun addNote(note: Note, callback: OnDataLocalCallback<Boolean>) {
        LocalAsyncTask<Note, Boolean>(callback) {
            noteDao.addNote(note)
        }.execute(note)
    }

    override fun updateNote(note: Note, callback: OnDataLocalCallback<Boolean>) {
        LocalAsyncTask<Note, Boolean>(callback) {
            noteDao.updateNote(note)
        }.execute(note)
    }

    override fun deleteNote(id: Int, callback: OnDataLocalCallback<Boolean>) {
        LocalAsyncTask<Int, Boolean>(callback) {
            noteDao.deleteNote(id)
        }.execute(id)
    }

    companion object {
        private var instance: NoteLocalDataSource? = null

        fun getInstance(noteDao: NoteDao): NoteLocalDataSource =
            instance ?: synchronized(this) {
                instance ?: NoteLocalDataSource(noteDao).also {
                    instance = it
                }
            }
    }
}
