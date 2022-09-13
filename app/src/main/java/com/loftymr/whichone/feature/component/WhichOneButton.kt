package com.loftymr.whichone.feature.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by talhafaki on 13.09.2022.
 */

@Composable
fun WhichOneButton(modifier: Modifier = Modifier, buttonText: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFEFEFE))
    ) {
        Text(
            text = buttonText,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center),
            style = MaterialTheme.typography.h1.copy(
                fontSize = 18.sp,
                color = Color(0xFF3552A2)
            )
        )
    }
}