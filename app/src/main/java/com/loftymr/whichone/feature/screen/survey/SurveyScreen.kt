@file:OptIn(ExperimentalAnimationApi::class)

package com.loftymr.whichone.feature.screen.survey

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loftymr.whichone.R
import com.loftymr.whichone.data.model.RingsOfThePowerResponse
import com.loftymr.whichone.data.model.SrcSet
import com.loftymr.whichone.feature.component.*
import com.loftymr.whichone.feature.theme.SurveyColor

/**
 * Created by talhafaki on 9.09.2022.
 */

@Composable
fun SurveyScreen(
    viewModel: SurveyViewModel = hiltViewModel(),
    navigateToResult: (String, String, String) -> Unit
) {
    val viewState = viewModel.uiState.collectAsState().value

    when {
        viewState.isLoading -> {
            CircularProgressAnimated()
        }
        viewState.isError -> {
            ErrorView(
                clickToRetry = {
                    viewModel.getRingsOfThePower()
                }
            )
        }
        viewState.data != null -> {
            WhichOneTemplate(
                topBar = {
                    WhichOneTopBar(
                        title = stringResource(id = R.string.survey_title),
                        backButtonEnabled = false
                    )
                },
                content = {
                    SurveyContent(
                        data = viewState.data,
                        showResult = { title, srcSet, desc ->
                            navigateToResult.invoke(title, srcSet?.nineHundred.orEmpty(), desc)
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
                viewModel.getRingsOfThePower()
            }
        }
    }
}

@Composable
fun SurveyContent(
    data: RingsOfThePowerResponse,
    showResult: (String, SrcSet?, String) -> Unit,
    adMob: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var questionIndex by remember {
        mutableStateOf(0)
    }
    val questionState = remember(questionIndex) {
        questionIndex
    }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 36.dp, bottom = 8.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(SurveyColor.Alabaster),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
        ) {
            Head(
                imageSource = data.backgroundPictures?.get(questionIndex).orEmpty(),
                numberOfSteps = data.questions?.lastIndex ?: 0,
                currentStep = questionIndex
            )

            AnimatedContent(
                targetState = questionState,
                transitionSpec = {
                    val animationSpec: TweenSpec<IntOffset> = tween(150)
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
                Column {
                    Text(
                        text = data.questions?.get(questionIndex)?.questionText.orEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 18.sp,
                            color = SurveyColor.Black,
                            textAlign = TextAlign.Center
                        )
                    )

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
                                                showResult.invoke(
                                                    data.character?.title.orEmpty(),
                                                    data.character?.srcSet,
                                                    data.character?.description.orEmpty()
                                                )
                                            }
                                        )
                                    }
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
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

@Composable
fun ErrorView(clickToRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SurveyColor.SolidBlue),
        verticalArrangement = Arrangement.Top
    ) {
        WhichOneAnim(rawResId = R.raw.opps_anim)

        Text(
            text = stringResource(id = R.string.something_went_wrong),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            style = MaterialTheme.typography.h1.copy(
                fontSize = 24.sp,
                color = SurveyColor.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        WhichOneButton(buttonText = stringResource(id = R.string.retry)) {
            clickToRetry.invoke()
        }
    }
}
