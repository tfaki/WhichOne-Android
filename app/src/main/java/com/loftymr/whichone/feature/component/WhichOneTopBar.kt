package com.loftymr.whichone.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.loftymr.whichone.R

/**
 * Created by talhafaki on 13.09.2022.
 */

@Composable
fun WhichOneTopBar(
    title: String,
    backButtonEnabled: Boolean = true,
    clickBack: () -> Unit = {}
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
            style = MaterialTheme.typography.body1.copy(color = Color.White),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}