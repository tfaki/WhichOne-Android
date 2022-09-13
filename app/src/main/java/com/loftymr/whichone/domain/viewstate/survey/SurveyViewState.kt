package com.loftymr.whichone.domain.viewstate.survey

import com.loftymr.whichone.data.model.RingsOfThePowerResponse
import com.loftymr.whichone.domain.viewstate.WhichOneViewState

/**
 * Created by talhafaki on 9.09.2022.
 */

data class SurveyViewState(
    var isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: RingsOfThePowerResponse? = null
) : WhichOneViewState