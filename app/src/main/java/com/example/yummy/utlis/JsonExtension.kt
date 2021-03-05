package com.example.yummy.utlis

import com.example.yummy.R
import com.example.yummy.data.model.*
import org.json.JSONArray
import org.json.JSONException

inline fun <reified T> String.parseJsonArray() = JSONArray(this).run {
    List(length()) { index ->
        when (T::class) {
            Category::class -> Category(getJSONObject(index)) as T
            Area::class -> Area(getJSONObject(index)) as T
            Ingredient::class -> Ingredient(getJSONObject(index)) as T
            Meal::class -> Meal(getJSONObject(index)) as T
            MealDetail::class -> MealDetail(getJSONObject(index)) as T
            else -> throw JSONException(R.string.error_parse_json.toString())
        }
    }
}
