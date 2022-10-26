package com.loftymr.whichone.domain.viewstate.result

import com.loftymr.whichone.domain.viewstate.WhichOneViewState

/**
 * Created by talhafaki on 26.10.2022.
 */

data class ResultViewState(
    var isLoading: Boolean = false
): WhichOneViewState
