package com.loftymr.whichone.feature.component

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val color = remember { Animatable(Color.Transparent) }
    var isClicked by remember { mutableStateOf(false) }
    if (isClicked) {
        LaunchedEffect(Unit) {
            color.animateTo(Color(0xFF005A70), animationSpec = tween(500))

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
                        .background(color = color.value, shape = RoundedCornerShape(8.dp))
                        .border(
                            border = BorderStroke(width = 1.dp, color = Color(0xFF3552A2)),
                            shape = RoundedCornerShape(8.dp)
                        )
                } else {
                    modifier
                        .background(color = Color.Transparent, shape = RoundedCornerShape(8.dp))
                        .border(
                            border = BorderStroke(width = 1.dp, color = Color.Black),
                            shape = RoundedCornerShape(8.dp)
                        )
                }
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .noRippleClickable {
                isClicked = true
            }
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxSize(),
            style = MaterialTheme.typography.body2.copy(
                color = if (isClicked) Color.White else Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
}