package com.loftymr.whichone.feature.component

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*

/**
 * Created by talhafaki on 13.09.2022.
 */

@Composable
fun WhichOneAnim(modifier: Modifier = Modifier, @RawRes rawResId: Int) {
    val composition =
        rememberLottieComposition(LottieCompositionSpec.RawRes(rawResId))

    val progress = animateLottieCompositionAsState(
        composition.value,
        iterations = LottieConstants.IterateForever,
        speed = 1f,
        restartOnPlay = false
    )

    LottieAnimation(
        composition = composition.value,
        progress = progress.value,
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}