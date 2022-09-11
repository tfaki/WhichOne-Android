package com.loftymr.whichone.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.loftymr.whichone.feature.screen.forceupdate.ForceUpdateScreen
import com.loftymr.whichone.feature.screen.result.ResultScreen
import com.loftymr.whichone.feature.screen.survey.SurveyScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * Created by talhafaki on 10.09.2022.
 */

@Composable
fun NavGraph(updateURL: String, navigateToPlayStore: () -> Unit) {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = if (updateURL.isEmpty()) "survey" else "forceUpdate"
    ) {
        composable(route = "survey") {
            SurveyScreen(
                navigateToResult = { title, srcSet ->
                    val encodedUrl = URLEncoder.encode(srcSet, StandardCharsets.UTF_8.toString())
                    navController.navigate("result/$encodedUrl/$title")
                }
            )
        }

        composable(
            route = "result/{imageSource}/{title}",
            arguments = listOf(
                navArgument("title") {
                    defaultValue = ""
                    type = NavType.StringType
                },
                navArgument("imageSource") {
                    defaultValue = ""
                    type = NavType.StringType
                }
            )) {
            ResultScreen(
                title = it.arguments?.getString("title").orEmpty(),
                character = it.arguments?.getString("imageSource").orEmpty(),
                navigateToSurvey = {
                    navController.navigate("survey")
                }
            )
        }

        composable(route = "forceUpdate") {
            ForceUpdateScreen(
                navigateToPlayStore = {
                    navigateToPlayStore.invoke()
                }
            )
        }
    }
}