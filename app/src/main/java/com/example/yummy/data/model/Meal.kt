package com.example.yummy.data.model

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.example.yummy.utlis.MealModelConst.ID_MEAL
import com.example.yummy.utlis.MealModelConst.STR_MEAL
import com.example.yummy.utlis.MealModelConst.STR_MEAL_THUMB
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Meal(
    val name: String,
    val image: String,
    val id: String,
    val timeLong: Long
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(STR_MEAL),
        jsonObject.getString(STR_MEAL_THUMB),
        jsonObject.getString(ID_MEAL), 0
    )

    constructor(cursor: Cursor) : this(
        cursor.getString(cursor.getColumnIndex(FAVORITE_KEY_NAME)),
        cursor.getString(cursor.getColumnIndex(FAVORITE_KEY_IMAGE)),
        cursor.getString(cursor.getColumnIndex(FAVORITE_KEY_ID)),
        cursor.getString(cursor.getColumnIndex(FAVORITE_TIME_LONG)).toLong()
    )

    fun getContentValue() = ContentValues().apply {
        put(FAVORITE_KEY_ID, id)
        put(FAVORITE_KEY_NAME, name)
        put(FAVORITE_KEY_IMAGE, image)
        put(FAVORITE_TIME_LONG, System.currentTimeMillis())
    }

    companion object {
        const val FAVORITE_TABLE_NAME = "favorite"
        const val FAVORITE_KEY_ID = "id"
        const val FAVORITE_KEY_NAME = "name"
        const val FAVORITE_KEY_IMAGE = "image"
        const val FAVORITE_TIME_LONG = "timeLong"
    }
}
