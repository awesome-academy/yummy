package com.example.yummy.data.model

import android.os.Parcelable
import com.example.yummy.utlis.AreaModelConst.STR_AREA
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Area(val name: String) : Parcelable {
    constructor(jsonObject: JSONObject) : this(jsonObject.getString(STR_AREA))
}
