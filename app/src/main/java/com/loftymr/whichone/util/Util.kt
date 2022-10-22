package com.loftymr.whichone.util

import android.os.Build
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.feature.theme.getThemeValue

/**
 * Created by tfakioglu on 22.10.2022.
 */
object Util {

    val isSupportsDynamic: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    @Composable
    fun setStatusBar() {
        val statusBarColor = if (isSupportsDynamic) {
            getThemeValue(
                darkValue = dynamicDarkColorScheme(LocalContext.current).onPrimary,
                lightValue = dynamicLightColorScheme(LocalContext.current).onPrimary
            )
        } else {
            getThemeValue(
                darkValue = SurveyColor.JordyBlue,
                lightValue = SurveyColor.White
            )
        }
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(
            color = statusBarColor
        )
    }
}