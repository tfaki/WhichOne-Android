package com.loftymr.whichone.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.feature.theme.WhichOneTheme
import com.loftymr.whichone.feature.theme.getThemeValue
import com.loftymr.whichone.util.Util

/**
 * Created by talhafaki on 25.10.2022.
 */

@Composable
fun WhichOneCard(imageSource: String, title: String, clickToSurvey: () -> Unit) {
    val color = if (Util.isSupportsDynamic) {
        getThemeValue(
            darkValue = dynamicDarkColorScheme(LocalContext.current).surface.copy(alpha = 0.8f),
            lightValue = dynamicLightColorScheme(LocalContext.current).surface.copy(alpha = 0.8f)
        )
    } else {
        getThemeValue(
            darkValue = SurveyColor.JordyBlue.copy(alpha = 0.8f),
            lightValue = SurveyColor.White.copy(alpha = 0.8f)
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clickable {
                clickToSurvey.invoke()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {

        Box {
            Image(
                painter = rememberAsyncImagePainter(imageSource),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .background(color = color)
                    .align(Alignment.BottomCenter),
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    style = WhichOneTheme.fontWhichOne.bold16.copy(
                        fontSize = 14.sp,
                        color = getThemeValue(
                            darkValue = SurveyColor.LightGray,
                            lightValue = SurveyColor.BlackSmoke
                        ),
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}