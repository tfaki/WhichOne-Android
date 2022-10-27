package com.loftymr.whichone.feature.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loftymr.whichone.feature.screen.forceupdate.ForceUpdateScreen
import com.loftymr.whichone.feature.screen.home.HomeScreen
import com.loftymr.whichone.feature.screen.result.ResultScreen
import com.loftymr.whichone.feature.screen.survey.SurveyScreen
import com.loftymr.whichone.feature.theme.Inter
import com.loftymr.whichone.feature.theme.SurveyColor
import com.loftymr.whichone.feature.theme.WhichOneTypo
import com.loftymr.whichone.feature.theme.localDarkTheme
import com.loftymr.whichone.feature.theme.localFontStyleWhichOne
import com.loftymr.whichone.util.extension.navigateSafe

/**
 * Created by talhafaki on 10.09.2022.
 */

@Composable
fun NavGraph(
    updateURL: String,
    navController: NavHostController,
    navigateToPlayStore: () -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()

    val fontWhichOne = WhichOneTypo(
        fontFamily = Inter,
        color = if (isDarkTheme) SurveyColor.White else SurveyColor.Nero
    )

    CompositionLocalProvider(
        localDarkTheme provides isDarkTheme,
        localFontStyleWhichOne provides fontWhichOne
    ) {
        NavHost(
            navController = navController,
            startDestination = if (updateURL.isEmpty()) WhichOneDirection.Home.route else WhichOneDirection.ForceUpdate.route,
        ) {
            homeNav(navController)
            surveyNav(navController)
            resultNav(navController)
            forceUpdateNav(navController, navigateToPlayStore)
        }
    }
}

private fun NavGraphBuilder.homeNav(
    navController: NavHostController
) {
    composable(route = WhichOneDirection.Home.route) {
        HomeScreen(
            navigateToSurvey = {
                navController.navigateSafe(
                    WhichOneDirection.Survey.routeSurvey(
                        it.title.orEmpty(),
                        it.catId.orEmpty()
                    )
                ) {}
            }
        )
    }
}

private fun NavGraphBuilder.surveyNav(
    navController: NavHostController
) {
    composable(
        route = WhichOneDirection.Survey.routeWithData,
        arguments = WhichOneDirection.Survey.arguments
    ) {
        SurveyScreen(
            id = WhichOneDirection.Survey.getId(it),
            title = WhichOneDirection.Survey.getTitle(it),
            navigateToResult = { character ->
                navController.navigateSafe(
                    WhichOneDirection.Result.routeResult(
                        title = character?.title.orEmpty(),
                        desc = character?.descriptionReason.orEmpty(),
                        imageSource = character?.srcSet.orEmpty()
                    )
                ) {}
            },
            clickToBack = {
                navController.popBackStack()
            }
        )
    }
}

private fun NavGraphBuilder.resultNav(
    navController: NavHostController
) {
    composable(
        route = WhichOneDirection.Result.routeWithData,
        arguments = WhichOneDirection.Result.arguments
    ) {
        ResultScreen(
            title = WhichOneDirection.Result.getTitle(it),
            desc = WhichOneDirection.Result.getDesc(it),
            imageSource = WhichOneDirection.Result.getImageSource(it),
            navigateToSurvey = { surveyId, surveyTitle ->
                navController.navigateSafe(
                    WhichOneDirection.Survey.routeSurvey(
                        title = surveyTitle,
                        id = surveyId
                    )
                ) {
                    popUpTo(WhichOneDirection.Home.route) {
                        inclusive = false
                    }
                }
            },
            navigateToHome = {
                navController.navigateSafe(
                    WhichOneDirection.Home.route
                ){
                    popUpTo(WhichOneDirection.Home.route){
                        inclusive = true
                    }
                }
            }
        )
    }
}

private fun NavGraphBuilder.forceUpdateNav(
    navController: NavHostController,
    navigateToPlayStore: () -> Unit
) {
    composable(route = WhichOneDirection.ForceUpdate.route) {
        ForceUpdateScreen(
            navigateToPlayStore = {
                navigateToPlayStore.invoke()
            }
        )
    }
}