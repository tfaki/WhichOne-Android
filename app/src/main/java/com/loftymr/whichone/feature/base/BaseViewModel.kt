package com.loftymr.whichone.feature.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loftymr.whichone.domain.viewstate.WhichOneViewEvent
import com.loftymr.whichone.domain.viewstate.WhichOneViewState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by talhafaki on 9.09.2022.
 */
abstract class BaseViewModel<State : WhichOneViewState, Event : WhichOneViewEvent> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    val currentState: State get() = uiState.value

    abstract fun onTriggerEvent(event: Event)

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState

    private val _uiEvent: MutableSharedFlow<Event> = MutableSharedFlow()
    val uiEvent = _uiEvent.asSharedFlow()

    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    protected fun setEvent(event: Event) {
        viewModelScope.launch { _uiEvent.emit(event) }
    }

    protected suspend fun <T> call(
        callFlow: Flow<T>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .catch { }
            .collect {
                completionHandler.invoke(it)
            }
    }
}