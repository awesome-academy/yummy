package com.example.yummy.data.model

import com.example.yummy.utlis.IngredientModelConst.ID_INGREDIENT
import com.example.yummy.utlis.IngredientModelConst.STR_DESCRIPTION
import com.example.yummy.utlis.IngredientModelConst.STR_INGREDIENT
import com.example.yummy.utlis.IngredientModelConst.STR_TYPE
import org.json.JSONObject

data class Ingredient(
    val id: String,
    val name: String,
    val image: String,
    val type: String
)  {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(ID_INGREDIENT),
        jsonObject.getString(STR_INGREDIENT),
        jsonObject.getString(STR_DESCRIPTION),
        jsonObject.getString(STR_TYPE)
    )
}
