package com.loftymr.whichone.feature.component

import android.app.Activity
import android.content.Context
import android.os.Handler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.loftymr.whichone.BuildConfig
import com.loftymr.whichone.R

/**
 * Created by talhafaki on 13.09.2022.
 */

private var mInterstitialAd: InterstitialAd? = null

@Composable
fun AdvertView(modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            AdView(context).apply {
                val bannerID =
                    if (BuildConfig.DEBUG) context.getString(R.string.ad_test_id_banner) else context.getString(
                        R.string.ad_id_banner
                    )
                setAdSize(AdSize.BANNER)
                adUnitId = bannerID
                loadAd(AdRequest.Builder().build())
            }
        }, update = {
            it.apply {
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}

fun loadInterstitial(context: Context, isFinished: (Boolean) -> Unit) {
    InterstitialAd.load(
        context,
        context.getString(if (BuildConfig.DEBUG) R.string.ad_test_id_interstitial else R.string.ad_id_interstitial),
        AdRequest.Builder().build(),
        object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
                isFinished.invoke(true)
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
                showInterstitial(context, isFinished)
            }
        }
    )
    Handler().postDelayed({
        if (mInterstitialAd == null) {
            isFinished.invoke(true)
        }
    }, 3500)
}

fun showInterstitial(context: Context, isFinished: (Boolean) -> Unit) {
    if (mInterstitialAd != null) {
        mInterstitialAd!!.show(context as Activity)

        addInterstitialCallbacks(isFinished = isFinished)
    } else {
        isFinished.invoke(true)
    }
}

// add the interstitial ad callbacks
fun addInterstitialCallbacks(isFinished: (Boolean) -> Unit) {
    mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            isFinished.invoke(true)
        }

        override fun onAdShowedFullScreenContent() {
            mInterstitialAd = null
            isFinished.invoke(false)
        }

        override fun onAdDismissedFullScreenContent() {
            isFinished.invoke(true)
        }
    }
}