@file:Suppress("DEPRECATION")

package com.example.yummy.data.source.remote.utlis

import android.os.AsyncTask

class RemoteAsyncTask<T>(
    private val callback: OnDataCallback<T>,
    private val handle: () -> T
) : AsyncTask<Unit, Unit, T?>() {

    private var exception: Exception? = null
    override fun doInBackground(vararg params: Unit): T? =
        try {
            handle()
        } catch (e: Exception) {
            exception = e
            null
        }

    override fun onPostExecute(result: T?) {
        result?.let(callback::onSuccess) ?: (callback.onFail(exception))
    }
}
