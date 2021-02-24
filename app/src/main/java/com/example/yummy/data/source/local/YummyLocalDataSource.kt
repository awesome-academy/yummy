package com.example.yummy.data.source.local

import com.example.yummy.data.source.FoodDataSource
import com.example.yummy.data.source.NoteDataSource
import com.example.yummy.data.source.local.dao.FavoriteDao
import com.example.yummy.data.source.local.dao.NoteDao
import com.example.yummy.data.source.local.utils.LocalAsyncTask
import com.example.yummy.data.source.local.utils.OnDataLocalCallback
import com.example.yummy.data.source.model.Favorite
import com.example.yummy.data.source.model.Food
import com.example.yummy.data.source.model.Note

class YummyLocalDataSource(
    private val noteDao: NoteDao,
    private val favoriteDao: FavoriteDao
) : NoteDataSource.Local, FoodDataSource.Local{
    override fun getAllNote(callback: OnDataLocalCallback<List<Note>>) {
        LocalAsyncTask<Unit, List<Note>>(callback){
            noteDao.getAllNote()
        }.execute()
    }

    override fun addNote(note: Note, callback: OnDataLocalCallback<Boolean>) {
        LocalAsyncTask<Note, Boolean>(callback){
            noteDao.addNote(note)
        }.execute(note)
    }

    override fun updateNote(note: Note, callback: OnDataLocalCallback<Boolean>) {
        LocalAsyncTask<Note, Boolean>(callback){
            noteDao.updateNote(note)
        }.execute(note)
    }

    override fun deleteNote(id: Int, callback: OnDataLocalCallback<Boolean>) {
        LocalAsyncTask<Int, Boolean>(callback){
            noteDao.deleteNote(id)
        }.execute(id)
    }

    override fun getAllFavorite(callback: OnDataLocalCallback<List<Favorite>>) {
        LocalAsyncTask<Unit, List<Favorite>>(callback){
            favoriteDao.getAllFavorite()
        }.execute()
    }

    override fun addFavorite(food: Food, callback: OnDataLocalCallback<Boolean>) {
        LocalAsyncTask<Food, Boolean>(callback){
            favoriteDao.addFavorite(food)
        }.execute(food)
    }

    override fun deleteFavorite(food: Food, callback: OnDataLocalCallback<Boolean>) {
        LocalAsyncTask<Food, Boolean>(callback){
            favoriteDao.deleteFavorite(food)
        }.execute(food)
    }

    companion object {
        private var instance: YummyLocalDataSource? = null

        fun getInstance(noteDao: NoteDao, favoriteDao: FavoriteDao): YummyLocalDataSource =
            instance ?: synchronized(this) {
                instance ?: YummyLocalDataSource(noteDao, favoriteDao).also {
                    instance = it
                }
            }
    }
}
