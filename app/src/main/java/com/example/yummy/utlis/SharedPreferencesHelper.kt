package com.example.yummy.utlis

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.yummy.R
import com.example.yummy.utlis.SharedPreferencesConst.PREFS_NAME

@Suppress("UNCHECKED_CAST")
class SharedPreferencesHelper private constructor(context: Context) {
    private var sharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    fun <T> put(key: String, data: T) {
        val editor = sharedPreferences.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Int -> editor.putInt(key, data)
        }
        editor.apply()
    }

    fun <T> get(key: String, data: T): T = when (data) {
        is String -> sharedPreferences.getString(key, null) as T
        is Boolean -> sharedPreferences.getBoolean(key, true) as T
        is Int -> sharedPreferences.getInt(key, 0) as T
        else -> throw TypeCastException(R.string.error_not_type.toString())
    }

    companion object {
        private var instance: SharedPreferencesHelper? = null
        fun getInstance(context: Context) =
            instance ?: SharedPreferencesHelper(context).also { instance = it }
    }
}
