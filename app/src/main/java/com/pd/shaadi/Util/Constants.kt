package com.pd.shaadi.Util

import androidx.lifecycle.LiveData

const val BASE_URL = "https://randomuser.me/"


fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = OneTimeObserver(handler = onChangeHandler)
    observe(observer, observer)
}