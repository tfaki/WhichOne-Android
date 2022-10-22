package com.loftymr.whichone.feature.screen.survey

import androidx.lifecycle.viewModelScope
import com.loftymr.whichone.data.remote.util.DataState
import com.loftymr.whichone.domain.repository.WhichOneRepository
import com.loftymr.whichone.domain.viewstate.WhichOneViewEvent
import com.loftymr.whichone.domain.viewstate.survey.SurveyViewState
import com.loftymr.whichone.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by talhafaki on 9.09.2022.
 */

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val whichOneRepository: WhichOneRepository
) : BaseViewModel<SurveyViewState, SurveyViewEvent>() {

    init {
        getRingsOfThePower()
    }

    fun getRingsOfThePower() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            delay(2000)
            whichOneRepository.ringsOfThePowerSurvey().collect {
                when (it) {
                    is DataState.Success -> {
                        setState {
                            if (it.data.character != null && !it.data.questions.isNullOrEmpty() && it.data.backgroundPictures?.isNotEmpty() == true) {
                                currentState.copy(
                                    data = it.data,
                                    isLoading = false,
                                    isError = false
                                )
                            } else {
                                currentState.copy(isLoading = false, isError = true)
                            }
                        }
                    }
                    is DataState.Loading -> {
                        setState { currentState.copy(isLoading = true, isError = false) }
                    }
                    is DataState.Error -> {
                        setState { currentState.copy(isLoading = false, isError = true) }
                    }
                }
            }
        }
    }

    fun showLoading(hasLoading: Boolean) {
        if (hasLoading) {
            setState { currentState.copy(isLoading = true) }
        } else {
            setState { currentState.copy(isLoading = false) }
        }
    }

    override fun createInitialState(): SurveyViewState = SurveyViewState()
    override fun onTriggerEvent(event: SurveyViewEvent) {}
}

sealed class SurveyViewEvent : WhichOneViewEvent {}