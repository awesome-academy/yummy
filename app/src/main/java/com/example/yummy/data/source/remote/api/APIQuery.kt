package com.example.yummy.data.source.remote.api

import android.net.Uri

object APIQuery {
    fun queryCategory() = Uri.Builder().scheme(APIConstants.SCHEME_HTTPS)
        .authority(APIConstants.AUTHORITY_API)
        .appendPath(APIConstants.CONTENT)
        .appendPath(APIConstants.CATEGORY)
        .toString()

    fun queryArea() = Uri.Builder().scheme(APIConstants.SCHEME_HTTPS)
        .authority(APIConstants.AUTHORITY_API)
        .appendPath(APIConstants.CONTENT)
        .appendPath(APIConstants.LIST)
        .appendQueryParameter(APIConstants.FILTER_AREA, APIConstants.VALUE_LIST)
        .toString()

    fun queryIngredient() = Uri.Builder().scheme(APIConstants.SCHEME_HTTPS)
        .authority(APIConstants.AUTHORITY_API)
        .appendPath(APIConstants.CONTENT)
        .appendPath(APIConstants.LIST)
        .appendQueryParameter(APIConstants.FILTER_INGREDIENT, APIConstants.VALUE_LIST)
        .toString()

    fun queryMealByCategory(meal: String) = Uri.Builder().scheme(APIConstants.SCHEME_HTTPS)
        .authority(APIConstants.AUTHORITY_API)
        .appendPath(APIConstants.CONTENT)
        .appendPath(APIConstants.FILTER)
        .appendQueryParameter(APIConstants.FILTER_CATEGORY, meal)
        .toString()

    fun queryMealByArea(meal: String) = Uri.Builder().scheme(APIConstants.SCHEME_HTTPS)
        .authority(APIConstants.AUTHORITY_API)
        .appendPath(APIConstants.CONTENT)
        .appendPath(APIConstants.FILTER)
        .appendQueryParameter(APIConstants.FILTER_AREA, meal)
        .toString()

    fun queryMealByIngredient(meal: String) = Uri.Builder().scheme(APIConstants.SCHEME_HTTPS)
        .authority(APIConstants.AUTHORITY_API)
        .appendPath(APIConstants.CONTENT)
        .appendPath(APIConstants.FILTER)
        .appendQueryParameter(APIConstants.FILTER_INGREDIENT, meal)
        .toString()

    fun querySearchMeal(wordSearch: String) = Uri.Builder().scheme(APIConstants.SCHEME_HTTPS)
        .authority(APIConstants.AUTHORITY_API)
        .appendPath(APIConstants.CONTENT)
        .appendPath(APIConstants.SEARCH)
        .appendQueryParameter(APIConstants.FILTER_SEARCH, wordSearch)
        .toString()
}
