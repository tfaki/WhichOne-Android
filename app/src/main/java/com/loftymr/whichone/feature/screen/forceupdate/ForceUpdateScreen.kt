package com.loftymr.whichone.feature.screen.forceupdate

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loftymr.whichone.R
import com.loftymr.whichone.feature.component.WhichOneAnim
import com.loftymr.whichone.feature.component.WhichOneButton
import com.loftymr.whichone.feature.component.WhichOneTemplate

/**
 * Created by talhafaki on 10.09.2022.
 */

@Composable
fun ForceUpdateScreen(navigateToPlayStore: () -> Unit) {
    WhichOneTemplate {
        ForceUpdateContent(
            navigateToPlayStore = {
                navigateToPlayStore.invoke()
            }
        )
    }
}

@Composable
fun ForceUpdateContent(navigateToPlayStore: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {

        WhichOneAnim(
            rawResId = R.raw.force_update_store_anim
        )

        Text(
            text = stringResource(id = R.string.new_version_available),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            style = MaterialTheme.typography.h1.copy(fontSize = 24.sp, color = Color.White)
        )

        Text(
            text = stringResource(id = R.string.new_version_available_desc),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.h1.copy(
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFFFEFEFE)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        WhichOneButton(buttonText = stringResource(id = R.string.go_to_play_store)) {
            navigateToPlayStore.invoke()
        }
    }
}