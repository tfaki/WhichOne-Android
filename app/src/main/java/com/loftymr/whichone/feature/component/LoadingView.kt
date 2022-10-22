package com.loftymr.whichone.feature.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.feature.theme.getThemeValue
import com.loftymr.whichone.util.Util

/**
 * Created by talhafaki on 13.09.2022.
 */

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    val progressValue = 1f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue, animationSpec = infiniteRepeatable(animation = tween(900))
    )

    val backgroundColor = if (Util.isSupportsDynamic) {
        getThemeValue(
            darkValue = dynamicDarkColorScheme(LocalContext.current).onPrimary,
            lightValue = dynamicLightColorScheme(LocalContext.current).onPrimary
        )
    } else {
        getThemeValue(
            darkValue = SurveyColor.JordyBlue,
            lightValue = SurveyColor.Transparent
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = backgroundColor
            ),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = progressAnimationValue,
            modifier = Modifier
                .wrapContentSize(),
            color = getThemeValue(
                darkValue = SurveyColor.LightGray,
                lightValue = SurveyColor.Biscay
            )
        )
    }
}