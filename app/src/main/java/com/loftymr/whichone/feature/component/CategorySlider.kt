package com.loftymr.whichone.feature.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.loftymr.whichone.data.model.CategoryResponse
import kotlin.math.absoluteValue

/**
 * Created by talhafaki on 25.10.2022.
 */


@OptIn(ExperimentalPagerApi::class)
@Composable
fun CategorySlider(list: List<CategoryResponse>, navigateToSurvey: (CategoryResponse) -> Unit) {

    val pagerState = rememberPagerState(initialPage = 0)
    var paddingValues = PaddingValues(start = 16.dp, end = 36.dp)

    if (pagerState.currentPage != 0) {
        paddingValues = PaddingValues(horizontal = 36.dp)
    }

    Column {

        HorizontalPager(
            contentPadding = paddingValues,
            count = list.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .fillMaxSize()
            ) {
                WhichOneCard(
                    imageSource = list[page].catImage.orEmpty(),
                    title = list[page].title.orEmpty(),
                    clickToSurvey = {
                        navigateToSurvey.invoke(list[page])
                    }
                )
            }
        }
    }
}