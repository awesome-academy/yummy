package com.example.yummy.data.source.remote.api

object APIConstants {
    const val SCHEME_HTTPS = "https"
    const val AUTHORITY_API = "www.themealdb.com"
    const val RSS = "https://cdn.24h.com.vn/upload/rss/amthuc.rss"

    const val CONTENT = "api/json/v1/1"
    const val CATEGORY = "categories.php"
    const val LIST = "list.php"
    const val SEARCH = "search.php"
    const val FILTER_SEARCH = "s"
    const val FILTER = "filter.php"
    const val FILTER_CATEGORY = "c"
    const val FILTER_AREA = "a"
    const val FILTER_INGREDIENT = "i"
    const val VALUE_LIST = "list"

    const val RSS_ITEM = "item"
    const val RSS_TITLE = "title"
    const val RSS_LINK = "guid"
    const val RSS_DESCRIPTION = "description"
    const val RSS_BR = "<br />"
    const val RSS_IMG_FORMAT = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>"
}
