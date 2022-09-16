package com.loftymr.whichone.feature.screen.result

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.loftymr.whichone.feature.component.WhichOneButton
import com.loftymr.whichone.feature.component.WhichOneTemplate
import com.loftymr.whichone.R

/**
 * Created by talhafaki on 10.09.2022.
 */

@Composable
fun ResultScreen(
    title: String = "",
    character: String = "",
    desc: String = "",
    navigateToSurvey: () -> Unit
) {
    WhichOneTemplate(
        topBar = {},
    ) {
        ResultContent(
            title = title,
            character = character,
            desc = desc,
            navigateToSurvey = {
                navigateToSurvey.invoke()
            }
        )
    }
    BackHandler {
        navigateToSurvey.invoke()
    }
}

@Composable
fun ResultContent(
    modifier: Modifier = Modifier,
    title: String = "",
    desc: String = "",
    character: String = "",
    navigateToSurvey: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = rememberImagePainter(character),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
        )

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.White,
            style = MaterialTheme.typography.h1.copy(color = Color.White, fontSize = 24.sp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = desc,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.White,
            style = MaterialTheme.typography.body1.copy(color = Color.White.copy(0.5f), fontSize = 16.sp, textAlign = TextAlign.Center)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            WhichOneButton(buttonText = stringResource(id = R.string.again)) {
                navigateToSurvey.invoke()
            }
        }
    }
}