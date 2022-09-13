package com.loftymr.whichone.feature.screen.survey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
    navigateToResult: (String, String) -> Unit
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
        else -> {
            WhichOneTemplate(
                topBar = {
                    WhichOneTopBar(title = "Rings Of Power", backButtonEnabled = false) {

                    }
                },
                content = {
                    SurveyContent(
                        data = viewState.data,
                        showResult = { title, srcSet ->
                            navigateToResult.invoke(title, srcSet?.nineHundred.orEmpty())
                        },
                        adMob = {
                            viewModel.showLoading(it)
                        }
                    )
                }
            )
        }
    }
}

@Composable
fun SurveyContent(
    data: RingsOfThePowerResponse?,
    showResult: (String, SrcSet?) -> Unit,
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
        ) {
            Head(
                imageSource = data.character?.srcSet?.nineHundred.orEmpty(),
                numberOfSteps = list.size - 1,
                currentStep = questionIndex
            )

            Text(
                text = list[questionIndex].questionText.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.h1.copy(fontSize = 18.sp, color = Color.White)
            )

            list[questionIndex].choices?.let { choices ->
                LazyColumn(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    items(choices) {
                        SelectionBox(
                            text = it,
                            isSelected = {
                                if (questionIndex < list.size - 1) {
                                    questionIndex++
                                } else {
                                    adMob.invoke(true)
                                    loadInterstitial(
                                        context = context,
                                        isFinished = {
                                            adMob.invoke(false)
                                            showResult.invoke(
                                                data.character?.title.orEmpty(),
                                                data.character?.srcSet
                                            )
                                        }
                                    )
                                }
                            }
                        )
                    }
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
    val verticalGradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFC8D7CF),
            Color(0xFF6987B4),
            Color(0xFF333D5F)
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = verticalGradientBrush),
        verticalArrangement = Arrangement.Top
    ) {
        WhichOneAnim(rawResId = R.raw.opps_anim)

        Text(
            text = "Something went wrong!",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            style = MaterialTheme.typography.h1.copy(fontSize = 24.sp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        WhichOneButton(buttonText = "RETRY") {
            clickToRetry.invoke()
        }
    }
}
