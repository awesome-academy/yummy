package com.example.yummy.data.model

import android.os.Parcelable
import com.example.yummy.utlis.IngredientModelConst.ID_INGREDIENT
import com.example.yummy.utlis.IngredientModelConst.STR_DESCRIPTION
import com.example.yummy.utlis.IngredientModelConst.STR_INGREDIENT
import com.example.yummy.utlis.IngredientModelConst.STR_TYPE
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Ingredient(
    val id: String,
    val name: String,
    val description: String?,
    val type: String
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(ID_INGREDIENT),
        jsonObject.getString(STR_INGREDIENT),
        jsonObject.getString(STR_DESCRIPTION),
        jsonObject.getString(STR_TYPE)
    )
}
