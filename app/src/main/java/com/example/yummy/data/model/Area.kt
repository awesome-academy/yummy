package com.example.yummy.data.model

import com.example.yummy.utlis.AreaModelConst.STR_AREA
import org.json.JSONObject

data class Area(val name: String) {
    constructor(jsonObject: JSONObject) : this(jsonObject.getString(STR_AREA))
}
