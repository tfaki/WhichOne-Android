package com.loftymr.whichone.feature.screen.survey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loftymr.whichone.R
import com.loftymr.whichone.data.model.RingsOfThePowerResponse
import com.loftymr.whichone.data.model.SrcSet
import com.loftymr.whichone.feature.component.*

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
                        showResult = { title, srcSet , desc->
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
    data: RingsOfThePowerResponse?,
    showResult: (String, SrcSet?, String) -> Unit,
    adMob: (Boolean) -> Unit
) {
    val context = LocalContext.current
    data?.questions?.let { list ->
        var questionIndex by remember {
            mutableStateOf(0)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, top = 36.dp, bottom = 8.dp)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(12.dp))
                .background(color = Color(0xFFFEFEFE), shape = RoundedCornerShape(12.dp))
                .verticalScroll(state = rememberScrollState())
        ) {
            Head(
                imageSource = data.backgroundPictures?.get(questionIndex).orEmpty(),
                numberOfSteps = list.lastIndex,
                currentStep = questionIndex
            )

            Text(
                text = list[questionIndex].questionText.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.h1.copy(fontSize = 18.sp, color = Color.Black,  textAlign = TextAlign.Center)
            )

            list[questionIndex].choices?.let { choices ->
                choices.forEach {
                    SelectionBox(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = it,
                        isSelected = {
                            if (questionIndex < list.lastIndex) {
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

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 12.dp)
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
            .background(color = Color(0xFF3552A2)),
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
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        WhichOneButton(buttonText = stringResource(id = R.string.retry)) {
            clickToRetry.invoke()
        }
    }
}
