package com.example.yummy.data.model

import com.example.yummy.utlis.CategoryModelConst.ID_CATEGORY
import com.example.yummy.utlis.CategoryModelConst.STR_CATEGORY
import com.example.yummy.utlis.CategoryModelConst.STR_CATEGORY_DESCRIPTION
import com.example.yummy.utlis.CategoryModelConst.STR_CATEGORY_THUMB
import org.json.JSONObject

data class Category(
    val id: String,
    val name: String,
    val image: String?,
    val description: String
) {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(ID_CATEGORY),
        jsonObject.getString(STR_CATEGORY),
        jsonObject.getString(STR_CATEGORY_THUMB),
        jsonObject.getString(STR_CATEGORY_DESCRIPTION)
    )
}
