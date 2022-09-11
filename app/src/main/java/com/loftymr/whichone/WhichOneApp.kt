package com.loftymr.whichone

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.loftymr.whichone.util.ForceUpdateChecker
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by talhafaki on 9.09.2022.
 */

@HiltAndroidApp
class WhichOneApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val firebaseRemoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        // set in-app defaults
        val remoteConfigDefaults: MutableMap<String, Any> = HashMap()
        remoteConfigDefaults[ForceUpdateChecker.KEY_UPDATE_REQUIRED] = false
        remoteConfigDefaults[ForceUpdateChecker.KEY_CURRENT_VERSION] = "1.0.0"
        remoteConfigDefaults[ForceUpdateChecker.KEY_UPDATE_URL] =
            "https://play.google.com/store/apps/details?id=com.fribbleapp.mathriddles&hl=tr&gl=US"
        firebaseRemoteConfig.setDefaultsAsync(remoteConfigDefaults)
        firebaseRemoteConfig.fetch(60) // fetch every minutes
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    firebaseRemoteConfig.fetchAndActivate()
                }
            }
    }
}