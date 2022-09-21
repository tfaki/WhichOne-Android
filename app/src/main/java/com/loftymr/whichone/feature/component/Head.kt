package com.loftymr.whichone.feature.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.loftymr.whichone.feature.theme.SurveyColor

/**
 * Created by talhafaki on 9.09.2022.
 */

@Composable
fun Head(imageSource: String, numberOfSteps: Int, currentStep: Int) {
    val animatedProgress by animateFloatAsState(
        targetValue = (currentStep + 1) / (numberOfSteps + 1).toFloat(),
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
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
    Spacer(modifier = Modifier.height(8.dp))
    LinearProgressIndicator(
        progress = animatedProgress,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        color = SurveyColor.DeepBlue,
        trackColor = SurveyColor.DeepBlue.copy(alpha = 0.12f),
    )
    Spacer(modifier = Modifier.height(12.dp))
}