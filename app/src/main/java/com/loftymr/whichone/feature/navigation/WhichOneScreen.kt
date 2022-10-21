package com.loftymr.whichone.feature.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by talhafaki on 21.10.2022.
 */

@Parcelize
enum class WhichOneScreen(
    val path: String
): Parcelable {
    SURVEY("survey"),
    RESULT("result"),
    FORCE_UPDATE("forceupdate")
}