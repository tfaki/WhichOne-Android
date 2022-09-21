package com.loftymr.whichone.feature.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.loftymr.whichone.R

// Set of Material typography styles to start with
val Fonts = FontFamily(
    Font(R.font.inter_bold),
    Font(R.font.inter_medium),
    Font(R.font.inter_regular)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = SurveyColor.Black
    ),
    h1 = TextStyle(
        fontFamily = Fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = SurveyColor.Black
    ),
    body2 = TextStyle(
        fontFamily = Fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = SurveyColor.Black
    )
)