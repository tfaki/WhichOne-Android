package com.loftymr.whichone.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.loftymr.whichone.R
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.feature.theme.WhichOneTheme
import com.loftymr.whichone.feature.theme.getThemeValue
import com.loftymr.whichone.util.Util

/**
 * Created by talhafaki on 13.09.2022.
 */

@Composable
fun WhichOneTopBar(
    title: String,
    backButtonEnabled: Boolean = true,
    clickBack: () -> Unit = {}
) {
    val backgroundColor = if (Util.isSupportsDynamic) {
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
            .fillMaxWidth()
            .background(color = backgroundColor)
    ) {
        if (backButtonEnabled) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp)
                    .clickable {
                        clickBack.invoke()
                    }
            )
        }

        Text(
            text = title,
            style = WhichOneTheme.fontWhichOne.normal16.copy(color = getThemeValue(
                darkValue = SurveyColor.White,
                lightValue = SurveyColor.Nero
            )),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}