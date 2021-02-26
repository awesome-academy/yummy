package com.example.yummy.data.source.local.utils

import java.lang.Exception

interface OnDataLocalCallback<T> {
    fun onSuccess(data: T)
    fun onFail(exception: Exception)
}
