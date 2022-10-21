package com.loftymr.whichone.feature.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.feature.theme.WhichOneTheme
import com.loftymr.whichone.feature.theme.getThemeValue

/**
 * Created by talhafaki on 9.09.2022.
 */
@Composable
fun Head(imageSource: String, questionText: String, numberOfSteps: Int, currentStep: Int) {
    val animatedProgress by animateFloatAsState(
        targetValue = currentStep / numberOfSteps.toFloat(),
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    Column(
        modifier = Modifier
            .background(
                color = getThemeValue(
                    darkValue = SurveyColor.JordyBlue,
                    lightValue = SurveyColor.White
                )
            )
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Image(
                painter = rememberImagePainter(imageSource),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Text(
            text = questionText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            style = WhichOneTheme.fontWhichOne.bold16.copy(
                fontSize = 18.sp,
                color = getThemeValue(
                    darkValue = SurveyColor.LightGray,
                    lightValue = SurveyColor.BlackSmoke
                ),
                textAlign = TextAlign.Start
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = getThemeValue(
                            darkValue = SurveyColor.Alabaster,
                            lightValue = SurveyColor.Nero
                        )
                    )
                ) {
                    append("Step $currentStep")
                }

                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = SurveyColor.Pantone
                    )
                ) {
                    append(" of $numberOfSteps")
                }
            },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .fillMaxWidth(),
            color = getThemeValue(
                darkValue = SurveyColor.DeepNavy,
                lightValue = SurveyColor.Navy
            ),
            trackColor = getThemeValue(
                darkValue = SurveyColor.LightGray,
                lightValue = SurveyColor.WhiteSmoke
            ),
        )
    }
}