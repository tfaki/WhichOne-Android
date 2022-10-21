package com.loftymr.whichone.feature.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.feature.theme.WhichOneTheme
import com.loftymr.whichone.feature.theme.getThemeValue

/**
 * Created by talhafaki on 13.09.2022.
 */

@Composable
fun WhichOneButton(modifier: Modifier = Modifier, buttonText: String, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Button(
            onClick = { onClick.invoke() },
            modifier = Modifier
                .fillMaxSize(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = getThemeValue(
                    darkValue = SurveyColor.Navy,
                    lightValue = SurveyColor.Bunker
                )
            )
        ) {
            Text(
                text = buttonText,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center),
                style = WhichOneTheme.fontWhichOne.bold16.copy(
                    fontSize = 18.sp,
                    color = getThemeValue(
                        darkValue = SurveyColor.Biscay,
                        lightValue = SurveyColor.White
                    )
                )
            )
        }
    }
}