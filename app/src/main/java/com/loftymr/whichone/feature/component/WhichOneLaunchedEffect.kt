package com.loftymr.whichone.feature.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.CoroutineScope

/**
 * Created by talhafaki on 25.10.2022.
 */

@Composable
fun WhichOneLaunchedEffect(
    key: Any? = Unit,
    block: suspend  CoroutineScope.(isInit: Boolean) -> Unit
) {
    var isInit by rememberSaveable { mutableStateOf(true) }
    LaunchedEffect(key) {
        block.invoke(this, isInit)
        isInit = false
    }
}