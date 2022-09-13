package com.loftymr.whichone.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

/**
 * Created by talhafaki on 9.09.2022.
 */

@Composable
fun Head(imageSource: String, numberOfSteps: Int, currentStep: Int) {
    Image(
        painter = rememberImagePainter(imageSource),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
    )
    Spacer(modifier = Modifier.height(8.dp))
    StepsProgressBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        numberOfSteps = numberOfSteps,
        currentStep = currentStep
    )
    Spacer(modifier = Modifier.height(12.dp))
}