package com.loftymr.whichone.feature.screen.splash

import androidx.lifecycle.viewModelScope
import com.loftymr.whichone.feature.base.BaseViewModel
import com.loftymr.whichone.domain.viewstate.WhichOneViewEvent
import com.loftymr.whichone.domain.viewstate.splash.SplashViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by talhafaki on 9.09.2022.
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel<SplashViewState, SplashViewEvent>() {

    init {
        viewModelScope.launch {
            delay(1000)
            setEvent(SplashViewEvent.DirectToDashBoard)
        }
    }

    override fun createInitialState() = SplashViewState()
    override fun onTriggerEvent(event: SplashViewEvent) {}
}

sealed class SplashViewEvent : WhichOneViewEvent {
    object DirectToDashBoard : SplashViewEvent()
}