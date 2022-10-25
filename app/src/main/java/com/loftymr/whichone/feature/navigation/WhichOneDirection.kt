package com.loftymr.whichone.feature.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.loftymr.whichone.util.extension.urlEncoded

/**
 * Created by talhafaki on 21.10.2022.
 */

sealed class WhichOneDirection(
    protected val whichOneScreen: WhichOneScreen
) {

    object Home : WhichOneDirection(
        whichOneScreen = WhichOneScreen.HOME
    ) {
        val route = whichOneScreen.path
    }

    object Survey : WhichOneDirection(
        whichOneScreen = WhichOneScreen.SURVEY
    ) {
        private const val argTitle = "title"
        private const val argId = "id"
        val routeWithData = "${whichOneScreen.path}/{${argTitle}}/{${argId}}"

        val arguments = listOf(
            navArgument(argTitle) {
                type = NavType.StringType
            },
            navArgument(argId) {
                type = NavType.StringType
            }
        )

        fun routeSurvey(
            title: String,
            id: String
        ): String {
            return "${whichOneScreen.path}/$title/$id"
        }

        fun getTitle(nbse: NavBackStackEntry): String {
            return nbse.arguments?.getString(argTitle).orEmpty()
        }

        fun getId(nbse: NavBackStackEntry): String {
            return nbse.arguments?.getString(argId).orEmpty()
        }
    }

    object Result : WhichOneDirection(
        whichOneScreen = WhichOneScreen.RESULT
    ) {
        private const val argTitle = "title"
        private const val argDesc = "desc"
        private const val argImageSource = "imageSource"
        val routeWithData = "${whichOneScreen.path}/{$argTitle}/{$argDesc}/{$argImageSource}"

        val arguments = listOf(
            navArgument(argTitle) {
                type = NavType.StringType
            },
            navArgument(argDesc) {
                type = NavType.StringType
            },
            navArgument(argImageSource) {
                type = NavType.StringType
            }
        )

        fun routeResult(
            title: String,
            desc: String,
            imageSource: String
        ): String {
            return "${whichOneScreen.path}/$title/$desc/${imageSource.urlEncoded()}"
        }

        fun getTitle(nbse: NavBackStackEntry): String {
            return nbse.arguments?.getString(argTitle).orEmpty()
        }

        fun getDesc(nbse: NavBackStackEntry): String {
            return nbse.arguments?.getString(argDesc).orEmpty()
        }

        fun getImageSource(nbse: NavBackStackEntry): String {
            return nbse.arguments?.getString(argImageSource).orEmpty()
        }
    }

    object ForceUpdate : WhichOneDirection(
        whichOneScreen = WhichOneScreen.FORCE_UPDATE
    ) {
        val route = whichOneScreen.path
    }
}