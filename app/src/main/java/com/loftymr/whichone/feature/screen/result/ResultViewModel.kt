package com.loftymr.whichone.feature.screen.result

import androidx.lifecycle.ViewModel
import com.loftymr.whichone.domain.viewstate.WhichOneViewEvent
import com.loftymr.whichone.domain.viewstate.result.ResultViewState
import com.loftymr.whichone.feature.base.BaseViewModel
import com.loftymr.whichone.util.AppCache
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by talhafaki on 25.10.2022.
 */
@HiltViewModel
class ResultViewModel @Inject constructor(
    val appCache: AppCache
): BaseViewModel<ResultViewState, ResultViewEvent>() {

    init {
        setState { currentState.copy(isLoading = true) }
    }

    fun hideLoading() {
        setState { currentState.copy(isLoading = false) }
    }

    override fun createInitialState(): ResultViewState = ResultViewState()
    override fun onTriggerEvent(event: ResultViewEvent) {}
}

sealed class ResultViewEvent : WhichOneViewEvent {}