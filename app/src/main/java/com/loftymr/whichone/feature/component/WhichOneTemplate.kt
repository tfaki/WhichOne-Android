package com.loftymr.whichone.feature.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Created by talhafaki on 13.09.2022.
 */

@Composable
fun WhichOneTemplate(
    topBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
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
                .background(color = Color(0xFF3552A2))
        )
    }
}