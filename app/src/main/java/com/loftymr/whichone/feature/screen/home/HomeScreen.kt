package com.loftymr.whichone.feature.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loftymr.whichone.R
import com.loftymr.whichone.data.model.Category
import com.loftymr.whichone.feature.component.CategorySlider
import com.loftymr.whichone.feature.component.LoadingView
import com.loftymr.whichone.feature.component.SearchView
import com.loftymr.whichone.feature.component.WhichOneTemplate
import com.loftymr.whichone.feature.component.WhichOneTopBar
import com.loftymr.whichone.feature.screen.survey.ErrorView
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.feature.theme.WhichOneTheme
import com.loftymr.whichone.feature.theme.getThemeValue
import com.loftymr.whichone.util.Util
import java.util.Locale

/**
 * Created by talhafaki on 25.10.2022.
 */

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToSurvey: (Category) -> Unit
) {

    Util.setStatusBar()
    val viewState = viewModel.uiState.collectAsState().value

    when {
        viewState.isLoading -> {
            LoadingView()
        }

        viewState.isError -> {
            ErrorView(
                clickToRetry = {
                    viewModel.getCategories()
                }
            )
        }

        viewState.data != null -> {
            WhichOneTemplate(
                topBar = {
                    WhichOneTopBar(
                        title = stringResource(id = R.string.app_name),
                        backButtonEnabled = false
                    )
                },
                content = {
                    HomeContent(
                        data = viewState.data,
                        navigateToSurvey = {
                            navigateToSurvey.invoke(it)
                        }
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    data: List<Category>,
    navigateToSurvey: (Category) -> Unit
) {
    var featuredList: ArrayList<Category>? = null
    val searchText = remember { mutableStateOf(TextFieldValue("")) }

    val filteredCategories = if (searchText.value.text.isEmpty()) {
        data
    } else {
        val resultList = ArrayList<Category>()
        for (category in data) {
            if (category.title.orEmpty().lowercase(Locale.getDefault())
                    .contains(searchText.value.text.lowercase(Locale.getDefault()))
            ) {
                resultList.add(category)
            }
        }
        resultList
    }

    val tvSeries = filteredCategories.filter { it.catId?.startsWith("d") == true }
    val movies = filteredCategories.filter { it.catId?.startsWith("f") == true }
    if (tvSeries.size > 20 && movies.size > 10) {
        featuredList = arrayListOf(
            tvSeries[23],
            movies[10],
            tvSeries[17],
            movies[9],
            tvSeries[20],
            movies[5],
            tvSeries[16],
            tvSeries[7]
        )
    }

    LazyColumn {

        item {
            SearchView(state = searchText)
        }

        item {
            featuredList?.let {
                Text(
                    text = stringResource(id = R.string.home_featured),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 16.dp),
                    style = WhichOneTheme.fontWhichOne.bold16.copy(
                        fontSize = 18.sp,
                        color = getThemeValue(
                            darkValue = SurveyColor.LightGray,
                            lightValue = SurveyColor.BlackSmoke
                        ),
                        textAlign = TextAlign.Start
                    )
                )
            }
        }

        featuredList?.let {
            item {
                CategorySlider(
                    list = it,
                    navigateToSurvey = {
                        navigateToSurvey.invoke(it)
                    }
                )
            }
        }

        item {
            if (tvSeries.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.home_tv_series),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 16.dp),
                    style = WhichOneTheme.fontWhichOne.bold16.copy(
                        fontSize = 18.sp,
                        color = getThemeValue(
                            darkValue = SurveyColor.LightGray,
                            lightValue = SurveyColor.BlackSmoke
                        ),
                        textAlign = TextAlign.Start
                    )
                )
            }
        }

        item {
            CategorySlider(
                list = tvSeries,
                navigateToSurvey = {
                    navigateToSurvey.invoke(it)
                }
            )
        }

        item {
            if (movies.isNotEmpty()) {

                Text(
                    text = stringResource(id = R.string.home_movies),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 16.dp),
                    style = WhichOneTheme.fontWhichOne.bold16.copy(
                        fontSize = 18.sp,
                        color = getThemeValue(
                            darkValue = SurveyColor.LightGray,
                            lightValue = SurveyColor.BlackSmoke
                        ),
                        textAlign = TextAlign.Start
                    )
                )
            }
        }

        item {
            CategorySlider(
                list = movies,
                navigateToSurvey = {
                    navigateToSurvey.invoke(it)
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(66.dp))
        }

    }
}
