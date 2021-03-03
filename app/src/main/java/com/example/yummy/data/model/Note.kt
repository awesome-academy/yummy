package com.example.yummy.data.model

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat
import java.util.*

@Parcelize
data class Note(
    val id: Int = 0,
    var title: String = "",
    var description: String = "",
    private val date: String = ""
) : Parcelable {
    constructor(cursor: Cursor) : this(
        id = cursor.getInt(cursor.getColumnIndex(NOTE_ID)),
        title = cursor.getString(cursor.getColumnIndex(NOTE_TITLE)),
        description = cursor.getString(cursor.getColumnIndex(NOTE_DESCRIPTION)),
        date = DateFormat.getDateInstance()
            .format(Date(cursor.getLong(cursor.getColumnIndex(NOTE_DATE))).time)
    )

    fun getContentValue() = ContentValues().apply {
        put(NOTE_TITLE, title)
        put(NOTE_DESCRIPTION, description)
        put(NOTE_DATE, System.currentTimeMillis())
    }

    companion object {
        const val NOTE_TABLE = "tb_note"
        const val NOTE_ID = "id"
        const val NOTE_TITLE = "title"
        const val NOTE_DESCRIPTION = "description"
        const val NOTE_DATE = "date"
    }
}
