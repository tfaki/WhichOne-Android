package com.loftymr.whichone.feature.component

import androidx.annotation.RawRes
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.*
import com.loftymr.whichone.R
import kotlinx.coroutines.delay

/**
 * Created by talhafaki on 9.09.2022.
 */

@Composable
fun WhichOneTemplate(
    topBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {

    val verticalGradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFC8D7CF),
            Color(0xFF6987B4),
            Color(0xFF333D5F)
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            backgroundColor = Color.Transparent,
            content = content,
            topBar = topBar,
            modifier = Modifier
                .fillMaxSize()
                .background(brush = verticalGradientBrush)
        )
    }
}

@Composable
fun WhichOneTopBar(
    title: String,
    backButtonEnabled: Boolean = true,
    clickBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (backButtonEnabled) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp)
                    .clickable {
                        clickBack.invoke()
                    }
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.body1.copy(color = Color.DarkGray),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}

@Composable
fun CircularProgressAnimated(modifier: Modifier = Modifier) {
    val progressValue = 1f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue, animationSpec = infiniteRepeatable(animation = tween(900))
    )

    val verticalGradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFC8D7CF),
            Color(0xFF6987B4),
            Color(0xFF333D5F)
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = verticalGradientBrush),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = progressAnimationValue,
            modifier = Modifier
                .wrapContentSize(),
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}

@Composable
fun SelectionBox(
    modifier: Modifier = Modifier,
    text: String,
    list: List<String>,
    index: Int,
    isSelected: () -> Unit
) {
    val color = remember { Animatable(Color.Transparent) }
    var isClicked by remember { mutableStateOf(false) }
    if (isClicked) {
        LaunchedEffect(Unit) {
            color.animateTo(Color(0xFF4DD4F5), animationSpec = tween(500))

            delay(500)
            isSelected.invoke()
            isClicked = false
        }
    }

    Spacer(modifier = Modifier.height(6.dp))
    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (isClicked) {
                    modifier
                        .clip(RoundedCornerShape(8.dp))
                } else {
                    modifier.border(
                        border = BorderStroke(width = 1.dp, color = Color.Black),
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            )
            .background(color = if (isClicked) color.value else Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                isClicked = true
            }
    ) {
        Text(
            text = list[index],
            modifier = Modifier
                .fillMaxSize(),
            style = MaterialTheme.typography.body2.copy(
                color = if (isClicked) Color.Black else Color.White,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
fun StepsProgressBar(modifier: Modifier = Modifier, numberOfSteps: Int, currentStep: Int) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (step in 0..numberOfSteps) {
            Step(
                modifier = Modifier.weight(1F),
                isCompete = step < currentStep,
                isCurrent = step == currentStep
            )
        }
    }
}

@Composable
fun Step(modifier: Modifier = Modifier, isCompete: Boolean, isCurrent: Boolean) {
    val color = if (isCompete || isCurrent) Color(0xFF4DD4F5) else Color.White
    val innerCircleColor = if (isCompete) Color(0xFF4DD4F5) else Color.White

    Box(modifier = modifier) {

        //Line
        Divider(
            modifier = Modifier.align(Alignment.CenterStart),
            color = color,
            thickness = 2.dp
        )

        //Circle
        Canvas(modifier = Modifier
            .size(15.dp)
            .align(Alignment.CenterEnd)
            .border(
                shape = CircleShape,
                width = 2.dp,
                color = color
            ),
            onDraw = {
                drawCircle(color = innerCircleColor)
            }
        )
    }
}

@Composable
fun Head(imageSource: String, numberOfSteps: Int, currentStep: Int) {
    Image(
        painter = rememberImagePainter(imageSource),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
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

@Composable
fun WhichOneButton(modifier: Modifier = Modifier, buttonText: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFC8D7CF))
    ) {
        Text(
            text = buttonText,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center),
            style = MaterialTheme.typography.h1.copy(
                fontSize = 18.sp,
                color = Color(0xFF333D5F)
            )
        )
    }
}