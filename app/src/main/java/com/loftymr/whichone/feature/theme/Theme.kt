package com.loftymr.whichone.feature.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf


object WhichOneTheme {
    val isDark: Boolean
        @Composable get() = localDarkTheme.current

    val fontWhichOne: WhichOneTypo
        @Composable get() = localFontStyleWhichOne.current
}

val localDarkTheme = compositionLocalOf<Boolean> { error("No DarkTheme info provided") }
val localFontStyleWhichOne = compositionLocalOf<WhichOneTypo> { error("No WhichOne Font provided") }

@Composable
fun <T> getThemeValue(darkValue: T, lightValue: T): T {
    return if (WhichOneTheme.isDark) darkValue else lightValue
}