package com.loftymr.whichone.feature.screen.result

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.loftymr.whichone.R
import com.loftymr.whichone.feature.component.WhichOneButton
import com.loftymr.whichone.feature.component.WhichOneTemplate
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.feature.theme.WhichOneTheme
import com.loftymr.whichone.feature.theme.getThemeValue

/**
 * Created by talhafaki on 10.09.2022.
 */

@Composable
fun ResultScreen(
    title: String?,
    desc: String?,
    imageSource: String?,
    navigateToSurvey: () -> Unit
) {
    WhichOneTemplate(
        topBar = {},
    ) {
        ResultContent(
            title = title.orEmpty(),
            character = imageSource.orEmpty(),
            desc = desc.orEmpty(),
            navigateToSurvey = {
                navigateToSurvey.invoke()
            }
        )
    }
    BackHandler {
        navigateToSurvey.invoke()
    }
}

@Composable
fun ResultContent(
    modifier: Modifier = Modifier,
    title: String = "",
    desc: String = "",
    character: String = "",
    navigateToSurvey: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(character),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = getThemeValue(
                darkValue = SurveyColor.Alabaster,
                lightValue = SurveyColor.Nero
            ),
            style = WhichOneTheme.fontWhichOne.bold16.copy(
                color = SurveyColor.Nero,
                fontSize = 24.sp
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = desc,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = getThemeValue(
                darkValue = SurveyColor.LightGray,
                lightValue = SurveyColor.Nero
            ),
            style = WhichOneTheme.fontWhichOne.normal16.copy(
                color = getThemeValue(
                    darkValue = SurveyColor.LightGray,
                    lightValue = SurveyColor.Nero.copy(0.5f)
                ),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            WhichOneButton(buttonText = stringResource(id = R.string.again)) {
                navigateToSurvey.invoke()
            }
        }
    }
}