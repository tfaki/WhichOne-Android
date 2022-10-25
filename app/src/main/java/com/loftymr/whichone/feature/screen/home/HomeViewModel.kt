package com.loftymr.whichone.feature.screen.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.loftymr.whichone.data.remote.util.DataState
import com.loftymr.whichone.domain.repository.WhichOneRepository
import com.loftymr.whichone.domain.viewstate.WhichOneViewEvent
import com.loftymr.whichone.domain.viewstate.home.HomeViewState
import com.loftymr.whichone.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by talhafaki on 25.10.2022.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val whichOneRepository: WhichOneRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<HomeViewState, HomeViewEvent>() {

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            delay(2000)
            whichOneRepository.getCategories().collect {
                when (it) {
                    is DataState.Success -> {
                        setState {
                            if (it.data.isNotEmpty()) {
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

    override fun createInitialState(): HomeViewState = HomeViewState()
    override fun onTriggerEvent(event: HomeViewEvent) {}

}

sealed class HomeViewEvent : WhichOneViewEvent