package com.loftymr.whichone.feature.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.loftymr.whichone.feature.screen.main.MainActivity

/**
 * Created by talhafaki on 9.09.2022.
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }

        lifecycleScope.launchWhenCreated {
            viewModel.uiEvent.collect {
                when (it) {
                    is SplashViewEvent.DirectToDashBoard -> {
                        startMainActivity()
                        finish()
                    }
                }
            }
        }
    }

    private fun startMainActivity() {
        val intent =
            Intent(Intent.ACTION_VIEW).setClassName(packageName, MainActivity::class.java.name)
        startActivity(intent)
    }
}