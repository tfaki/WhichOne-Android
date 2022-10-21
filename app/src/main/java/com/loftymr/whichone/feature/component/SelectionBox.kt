package com.loftymr.whichone.feature.component

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.feature.theme.WhichOneTheme
import com.loftymr.whichone.feature.theme.getThemeValue
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
    val color = remember { Animatable(SurveyColor.Transparent) }
    var isClicked by remember { mutableStateOf(false) }
     val animateColor = getThemeValue(
         darkValue = SurveyColor.DeepNavy,
         lightValue = SurveyColor.Navy
     )
    if (isClicked) {
        LaunchedEffect(Unit) {
            color.animateTo(animateColor, animationSpec = tween(150))
            delay(500)
            isSelected.invoke()
            isClicked = false
        }
    }

    Spacer(modifier = Modifier.height(6.dp))
    val backgroundColor = if (isClicked) color.value else getThemeValue(
        darkValue = SurveyColor.JordyBlue,
        lightValue = SurveyColor.White
    )
    val borderStroke = if (isClicked) BorderStroke(
        width = 1.dp,
        color = SurveyColor.White
    ) else BorderStroke(width = 1.dp, color = SurveyColor.Pantone)

    val textColor = if (isClicked) {
        getThemeValue(
            darkValue = SurveyColor.White,
            lightValue = SurveyColor.White)
    } else {
        getThemeValue(
            darkValue = SurveyColor.LightGray,
            lightValue = SurveyColor.BlackSmoke)
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable {
                isClicked = true
            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(backgroundColor),
        border = borderStroke
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            style = WhichOneTheme.fontWhichOne.medium16.copy(
                color = textColor,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
}