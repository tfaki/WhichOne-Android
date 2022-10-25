package com.loftymr.whichone.feature.screen.result

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.loftymr.whichone.R
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
fun ResultScreen(
    title: String?,
    desc: String?,
    imageSource: String?,
    viewModel: ResultViewModel = hiltViewModel(),
    navigateToSurvey: (String, String) -> Unit,
    navigateToHome: () -> Unit
) {
    WhichOneTemplate(
        topBar = {},
    ) {
        ResultContent(
            title = title.orEmpty(),
            character = imageSource.orEmpty(),
            desc = desc.orEmpty(),
            navigateToSurvey = {
                navigateToSurvey.invoke(
                    viewModel.appCache.surveyId.orEmpty(),
                    viewModel.appCache.surveyTitle.orEmpty()
                )
            },
            navigateToHome = {
                navigateToHome.invoke()
            }
        )
    }
    BackHandler {
        navigateToSurvey.invoke(
            viewModel.appCache.surveyId.orEmpty(),
            viewModel.appCache.surveyTitle.orEmpty()
        )
    }
}

@Composable
fun ResultContent(
    modifier: Modifier = Modifier,
    title: String = "",
    desc: String = "",
    character: String = "",
    navigateToSurvey: () -> Unit,
    navigateToHome: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(character),
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

        Spacer(modifier = Modifier.height(6.dp))
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
        Column(
            modifier = Modifier
                .padding(bottom = 8.dp, top = 24.dp)
        ) {
            WhichOneButton(
                buttonText = stringResource(id = R.string.again),
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
                navigateToSurvey.invoke()
            }

            Spacer(modifier = Modifier.height(8.dp))

            WhichOneButton(
                buttonText = stringResource(id = R.string.home),
                buttonBackground = if (Util.isSupportsDynamic) {
                    getThemeValue(
                        darkValue = dynamicDarkColorScheme(LocalContext.current).secondaryContainer,
                        lightValue = dynamicLightColorScheme(LocalContext.current).secondaryContainer
                    )
                } else {
                    getThemeValue(
                        darkValue = SurveyColor.White,
                        lightValue = SurveyColor.DeepNavy
                    )
                }
            ) {
                navigateToHome.invoke()
            }
        }
    }
}