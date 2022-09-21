package com.loftymr.whichone.feature.component

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.util.extension.noRippleClickable
import kotlinx.coroutines.delay

/**
 * Created by talhafaki on 13.09.2022.
 */

@Composable
fun SelectionBox(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: () -> Unit
) {
    val color = remember { Animatable(SurveyColor.White) }
    var isClicked by remember { mutableStateOf(false) }
    if (isClicked) {
        LaunchedEffect(Unit) {
            color.animateTo(SurveyColor.DeepBlue, animationSpec = tween(150))
            delay(500)
            isSelected.invoke()
            isClicked = false
        }
    }

    Spacer(modifier = Modifier.height(6.dp))
    val backgroundColor = if (isClicked) color.value else SurveyColor.White
    val borderStroke = if (isClicked) BorderStroke(
        width = 1.dp,
        color = SurveyColor.SolidBlue
    ) else BorderStroke(width = 1.dp, color = SurveyColor.Black)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable {
                isClicked = true
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(backgroundColor),
        border = borderStroke
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            style = MaterialTheme.typography.body2.copy(
                color = if (isClicked) SurveyColor.White else SurveyColor.Black,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
}