package com.loftymr.whichone.feature.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.loftymr.whichone.R

// Set of Material typography styles to start with
val Inter = FontFamily(
    Font(R.font.inter_bold),
    Font(R.font.inter_medium),
    Font(R.font.inter_regular)
)

class WhichOneTypo(
    val fontFamily: FontFamily,
    val color: Color
) {
    val normal16 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = color
    )

    val bold16 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = color
    )

    val medium16 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = color
    )
}