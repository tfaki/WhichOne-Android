package com.loftymr.whichone.feature.screen.forceupdate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loftymr.whichone.R
import com.loftymr.whichone.feature.component.WhichOneAnim
import com.loftymr.whichone.feature.component.WhichOneButton
import com.loftymr.whichone.feature.component.WhichOneTemplate
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.feature.theme.WhichOneTheme
import com.loftymr.whichone.feature.theme.getThemeValue
import com.loftymr.whichone.util.Util

/**
 * Created by talhafaki on 10.09.2022.
 */

@Composable
fun ForceUpdateScreen(navigateToPlayStore: () -> Unit) {
    WhichOneTemplate {
        ForceUpdateContent(
            navigateToPlayStore = {
                navigateToPlayStore.invoke()
            }
        )
    }
}

@Composable
fun ForceUpdateContent(navigateToPlayStore: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {

        WhichOneAnim(
            rawResId = R.raw.force_update_store_anim
        )

        Text(
            text = stringResource(id = R.string.new_version_available),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            style = WhichOneTheme.fontWhichOne.bold16.copy(
                fontSize = 24.sp,
                color = getThemeValue(
                    darkValue = SurveyColor.White,
                    lightValue = SurveyColor.Biscay
                )
            )
        )

        Text(
            text = stringResource(id = R.string.new_version_available_desc),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            style = WhichOneTheme.fontWhichOne.bold16.copy(
                textAlign = TextAlign.Center,
                color = getThemeValue(
                    darkValue = SurveyColor.LightGray,
                    lightValue = SurveyColor.JordyBlue
                )
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            WhichOneButton(
                buttonText = stringResource(id = R.string.go_to_play_store),
                buttonBackground = if (Util.isSupportsDynamic) {
                    getThemeValue(
                        darkValue = dynamicDarkColorScheme(LocalContext.current).secondaryContainer,
                        lightValue = dynamicLightColorScheme(LocalContext.current).secondaryContainer
                    )
                } else {
                    getThemeValue(
                        darkValue = SurveyColor.Navy,
                        lightValue = SurveyColor.Bunker
                    )
                }
            ) {
                navigateToPlayStore.invoke()
            }
        }
    }
}