package com.loftymr.whichone.data.remote.util

/**
 * Created by talhafaki on 9.09.2022.
 */

sealed class DataState<T> {
    class Success<T>(val data: T) : DataState<T>()
    class Loading<T> : DataState<T>()
    class Error<T>(val exception: Exception) : DataState<T>()
}