package com.example.yummy.data.source.remote.utlis

import java.lang.Exception

interface OnDataCallback<T> {
    fun onSuccess(data: T)
    fun onFail(exception: Exception?)
}
