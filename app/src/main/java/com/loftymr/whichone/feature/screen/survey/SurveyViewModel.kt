package com.loftymr.whichone.feature.screen.survey

import androidx.lifecycle.viewModelScope
import com.loftymr.whichone.data.remote.util.DataState
import com.loftymr.whichone.domain.repository.SurveyRepository
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
    private val surveyRepository: SurveyRepository
) : BaseViewModel<SurveyViewState, SurveyViewEvent>() {

    init {
        getRingsOfThePower()
    }

    fun getRingsOfThePower() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            delay(2000)
            surveyRepository.ringsOfThePowerSurvey().collect {
                when (it) {
                    is DataState.Success -> {
                        setState {
                            currentState.copy(
                                data = it.data,
                                isLoading = false,
                                isError = false
                            )
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

    override fun createInitialState(): SurveyViewState = SurveyViewState()
    override fun onTriggerEvent(event: SurveyViewEvent) {}
}

sealed class SurveyViewEvent : WhichOneViewEvent {}