package com.example.yummy.utlis

import android.content.Context
import com.example.yummy.data.repository.FoodRepository
import com.example.yummy.data.repository.NoteRepository
import com.example.yummy.data.source.local.FoodLocalDataSource
import com.example.yummy.data.source.local.NoteLocalDataSource
import com.example.yummy.data.source.local.dao.FavoriteDaoImpl
import com.example.yummy.data.source.local.dao.NoteDaoImp
import com.example.yummy.data.source.local.db.AppDatabase
import com.example.yummy.data.source.remote.FoodRemoteDataSource

object RepositoryUtils {
    fun getFoodRepository(context: Context): FoodRepository {
        val database = AppDatabase.getInstance(context)
        val local = FoodLocalDataSource.getInstance(FavoriteDaoImpl.getInstance(database))
        val remote = FoodRemoteDataSource.getInstance()
        return FoodRepository.getInstance(local, remote)
    }

    fun getNoteRepository(context: Context): NoteRepository {
        val database = AppDatabase.getInstance(context)
        val local = NoteLocalDataSource.getInstance(NoteDaoImp.getInstance(database))
        return NoteRepository.getInstance(local)
    }
}
