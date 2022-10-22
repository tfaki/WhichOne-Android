package com.loftymr.whichone.feature.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.feature.theme.getThemeValue
import com.loftymr.whichone.util.Util

/**
 * Created by talhafaki on 13.09.2022.
 */

@Composable
fun WhichOneTemplate(
    topBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    val color = if (Util.isSupportsDynamic) {
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
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            backgroundColor = SurveyColor.Transparent,
            content = content,
            topBar = topBar,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = color
                )
        )
    }
}