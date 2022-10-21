package com.loftymr.whichone.util.extension

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by talhafaki on 21.10.2022.
 */

inline fun <reified T> String?.fromJson(gson: Gson = Gson()): T? {
    return tryCatch {
        gson.fromJson<T>(this, object : TypeToken<T>() {}.type)
    }
}

fun Any.toJson(): String? {
    return Gson().toJson(this)
}

fun <T, R> T.tryCatch(priority: Int? = Log.ERROR, block: (T) -> R): R? {
    return try {
        block(this)
    } catch (t: Throwable) {
        priority?.let { Log.d("catch", it.toString()) }
        null
    }
}