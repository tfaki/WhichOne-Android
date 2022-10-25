@file:OptIn(ExperimentalAnimationApi::class)

package com.loftymr.whichone.feature.screen.survey

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loftymr.whichone.R
import com.loftymr.whichone.data.model.Character
import com.loftymr.whichone.data.model.SurveyResponse
import com.loftymr.whichone.feature.component.AdvertView
import com.loftymr.whichone.feature.component.Head
import com.loftymr.whichone.feature.component.LoadingView
import com.loftymr.whichone.feature.component.SelectionBox
import com.loftymr.whichone.feature.component.WhichOneAnim
import com.loftymr.whichone.feature.component.WhichOneButton
import com.loftymr.whichone.feature.component.WhichOneLaunchedEffect
import com.loftymr.whichone.feature.component.WhichOneTemplate
import com.loftymr.whichone.feature.component.WhichOneTopBar
import com.loftymr.whichone.feature.component.loadInterstitial
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.feature.theme.WhichOneTheme
import com.loftymr.whichone.feature.theme.getThemeValue
import com.loftymr.whichone.util.Util

/**
 * Created by talhafaki on 9.09.2022.
 */

@Composable
fun SurveyScreen(
    viewModel: SurveyViewModel = hiltViewModel(),
    title: String,
    id: String,
    navigateToResult: (Character?) -> Unit,
    clickToBack: () -> Unit
) {
    WhichOneLaunchedEffect {
        viewModel.getSurvey(id)
    }

    val viewState = viewModel.uiState.collectAsState().value

    when {
        viewState.isLoading -> {
            LoadingView()
        }

        viewState.isError -> {
            ErrorView(
                clickToRetry = {
                    viewModel.getSurvey(id)
                }
            )
        }

        viewState.data != null -> {
            WhichOneTemplate(
                topBar = {
                    WhichOneTopBar(
                        title = title,
                        backButtonEnabled = true,
                        clickBack = {
                            clickToBack.invoke()
                        }
                    )
                },
                content = {
                    SurveyContent(
                        data = viewState.data,
                        navigateToResult = { srcSet ->
                            viewModel.appCache.surveyId = id
                            viewModel.appCache.surveyTitle = title
                            navigateToResult.invoke(srcSet)
                        },
                        adMob = {
                            viewModel.showLoading(it)
                        }
                    )
                }
            )
        }

        else -> {
            ErrorView {
                viewModel.getSurvey(id)
            }
        }
    }
}

@Composable
fun SurveyContent(
    data: SurveyResponse,
    navigateToResult: (Character?) -> Unit,
    adMob: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var questionIndex by remember {
        mutableStateOf(0)
    }
    val questionState = remember(questionIndex) {
        questionIndex
    }
    val rootBackground = if (Util.isSupportsDynamic) {
        getThemeValue(
            darkValue = dynamicDarkColorScheme(LocalContext.current).surface,
            lightValue = dynamicLightColorScheme(LocalContext.current).surface
        )
    } else {
        getThemeValue(
            darkValue = SurveyColor.Biscay,
            lightValue = SurveyColor.Alabaster
        )
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = rootBackground)
    ) {

        item {
            Head(
                imageSource = data.backgroundPictures?.get(0).orEmpty(),
                questionText = data.questions?.get(questionIndex)?.questionText.orEmpty(),
                numberOfSteps = data.questions?.size ?: 0,
                currentStep = questionIndex + 1
            )
        }

        item {
            AnimatedContent(
                targetState = questionState,
                transitionSpec = {
                    val animationSpec: TweenSpec<IntOffset> = tween(200)
                    val direction =
                        if (targetState > initialState) {
                            AnimatedContentScope.SlideDirection.Left
                        } else {
                            AnimatedContentScope.SlideDirection.Right
                        }
                    slideIntoContainer(
                        towards = direction,
                        animationSpec = animationSpec
                    ) with
                            slideOutOfContainer(
                                towards = direction,
                                animationSpec = animationSpec
                            )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = rootBackground)
                        .padding(top = 12.dp)
                ) {
                    data.questions?.get(questionIndex)?.choices?.let { choices ->
                        choices.forEach {
                            SelectionBox(
                                modifier = Modifier
                                    .padding(horizontal = 12.dp),
                                text = it,
                                isSelected = {
                                    if (questionIndex < data.questions.lastIndex) {
                                        questionIndex++
                                    } else {
                                        adMob.invoke(true)
                                        loadInterstitial(
                                            context = context,
                                            isFinished = {
                                                adMob.invoke(false)
                                                navigateToResult.invoke(data.character)
                                            }
                                        )
                                    }
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 2.dp)
                            .weight(1f),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        AdvertView(modifier = Modifier.wrapContentSize())
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorView(clickToRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = getThemeValue(
                    darkValue = SurveyColor.JordyBlue,
                    lightValue = SurveyColor.Alabaster
                )
            ),
        verticalArrangement = Arrangement.Top
    ) {
        WhichOneAnim(rawResId = R.raw.opps_anim)

        Text(
            text = stringResource(id = R.string.something_went_wrong),
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

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            WhichOneButton(
                buttonText = stringResource(id = R.string.retry),
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
                clickToRetry.invoke()
            }
        }
    }
}
