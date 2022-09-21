package com.loftymr.whichone.domain.repository

import com.loftymr.whichone.data.model.RingsOfThePowerResponse
import com.loftymr.whichone.data.remote.source.BaseRemoteDataSource
import com.loftymr.whichone.data.remote.api.SurveyService
import com.loftymr.whichone.data.remote.util.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by talhafaki on 9.09.2022.
 */

class SurveyRepository @Inject constructor(
    private val surveyService: SurveyService
) : BaseRemoteDataSource() {

    suspend fun ringsOfThePowerSurvey(): Flow<DataState<RingsOfThePowerResponse>> =
        getResult {
            surveyService.getRingsOfThePowerSurveys()
        }
}