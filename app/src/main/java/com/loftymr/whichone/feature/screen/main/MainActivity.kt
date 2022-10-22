package com.loftymr.whichone.feature.screen.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import com.loftymr.whichone.feature.navigation.NavGraph
import com.loftymr.whichone.util.ForceUpdateChecker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var navigateURL = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        ForceUpdateChecker.with(this)
            .onUpdateNeeded(object : ForceUpdateChecker.OnUpdateNeededListener {
                override fun onUpdateNeeded(updateUrl: String?) {
                    navigateURL = updateUrl.orEmpty()
                }

            }).check()
        setContent {
            val navController = rememberNavController()

            NavGraph(
                updateURL = navigateURL,
                navController = navController,
                navigateToPlayStore = {
                    redirectStore(navigateURL)
                }
            )
        }
        MobileAds.initialize(this)
    }

    private fun redirectStore(updateUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}