package com.example.yummy.data.source.local.utils

import android.os.AsyncTask
import android.util.Log

class LocalAsyncTask<V,T>(
    private val callBack: OnDataLocalCallback<T>,
    private val handle: (V) -> T
) : AsyncTask<V, Unit, T?>() {
    private var exception: java.lang.Exception? = null

    override fun doInBackground(vararg params: V): T? =
        try {
            handle(params[0])
                ?: throw Exception("Error get local data")
        } catch (e: java.lang.Exception) {
            exception = e
            null
        }

    override fun onPostExecute(result: T?) {
        result?.let(callBack::onSuccess) ?: (callBack::onFail)
    }
}
