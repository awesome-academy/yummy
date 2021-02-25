package com.example.yummy.data.model

import com.example.yummy.utlis.MealModelConst.ID_MEAL
import com.example.yummy.utlis.MealModelConst.STR_MEAL
import com.example.yummy.utlis.MealModelConst.STR_MEAL_THUMB
import org.json.JSONObject

data class Meal(
    val name: String,
    val image: String,
    val id: String,
    val timeLong: Long
) {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(STR_MEAL),
        jsonObject.getString(STR_MEAL_THUMB),
        jsonObject.getString(ID_MEAL), 0
    )
}
