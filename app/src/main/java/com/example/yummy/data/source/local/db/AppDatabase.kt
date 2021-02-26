package com.example.yummy.data.source.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.yummy.data.source.model.Note

class AppDatabase private constructor(
    context: Context?,
    dbName: String,
    version: Int
) : SQLiteOpenHelper(context, dbName, null, version) {

    override fun onCreate(database: SQLiteDatabase?) {
        database?.apply {
            execSQL(CREATE_NOTE_TABLE)
        }
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        database?.apply {
            execSQL(DROP_NOTE_TABLE)
        }
        onCreate(database)
    }

    companion object {
        private const val DB_NAME = "yummy.db"
        private const val DB_VERSION = 1

        private const val CREATE_NOTE_TABLE =
            "CREATE TABLE ${Note.NOTE_TABLE} (" +
                    "${Note.NOTE_ID} INTEGER PRIMARY KEY, " +
                    "${Note.NOTE_TITLE} INT, " +
                    "${Note.NOTE_DESCRIPTION} INT, " +
                    "${Note.NOTE_DATE} Long )"

        private const val DROP_NOTE_TABLE = "DROP TABLE IF EXISTS " + Note.NOTE_TABLE

        private val lock = Any()
        private var instance: AppDatabase? = null
        fun getInstance(context: Context?) =
            instance ?: synchronized(lock) {
                instance ?: AppDatabase(context, DB_NAME, DB_VERSION).also { instance = it }
            }
    }
}
