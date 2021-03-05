package com.example.yummy.data.model

import android.os.Parcelable
import com.example.yummy.utlis.MealDetailConst
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
class MealDetail(
    val area: String,
    val category: String,
    val Ingredients: String = ""
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(MealDetailConst.MEAL_DETAIL_AREA),
        jsonObject.getString(MealDetailConst.MEAL_DETAIL_CATEGORY),
        getIngredientIndex(MealDetailConst.MEAL_DETAIL_INGREDIENT, jsonObject)
    )

    companion object {
        fun getIngredientIndex(key: String, jsonObject: JSONObject): String {
            var ingredientString = "»  "
            for (i in 1..20) {
                if (jsonObject.getString("$key$i")
                        .isEmpty() || jsonObject.getString("$key$i") == "null"
                )
                    return ingredientString.substring(0, (ingredientString.length - 2))
                else {
                    ingredientString += String.format("%s, ", jsonObject.getString("$key$i"))
                }
            }
            return ingredientString.substring(0, (ingredientString.length - 2))
        }
    }
}
