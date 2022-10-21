package com.loftymr.whichone.util.extension

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

/**
* Created by talhafaki on 21.10.2022.
*/

fun NavController.navigateSafe(
    route: String,
    builder: NavOptionsBuilder.() -> Unit
) {
    try {
        navigate(
            route = route,
            builder = builder
        )
    } catch (e: Throwable) {

    }
}