package com.example.yummy.data.source.model

import android.content.ContentValues
import android.database.Cursor

data class Favorite(
    val id: Int = 0,
    val foodId: Int,
    val time: Long = 0
) {
    constructor(cursor: Cursor) : this(
        id = cursor.getInt(cursor.getColumnIndex(FAVORITE_ID)),
        foodId = cursor.getInt(cursor.getColumnIndex(FAVORITE_FOOD_ID)),
        time = cursor.getLong(cursor.getColumnIndex(FAVORITE_TIME))
    )

    fun getContentValue() = ContentValues().apply {
        put(FAVORITE_ID, id)
        put(FAVORITE_FOOD_ID, foodId)
        put(FAVORITE_TIME, System.currentTimeMillis())
    }

    companion object {
        const val FAVORITE_TABLE = "tb_favorite_food"
        const val FAVORITE_ID = "id"
        const val FAVORITE_FOOD_ID = "food_id"
        const val FAVORITE_TIME = "time"
    }
}
