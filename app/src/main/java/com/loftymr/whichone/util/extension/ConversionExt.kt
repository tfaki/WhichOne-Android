package com.loftymr.whichone.util.extension

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * Created by talhafaki on 21.10.2022.
 */

fun String.urlEncoded(): String? {
    return tryCatch {
        URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
    }
}