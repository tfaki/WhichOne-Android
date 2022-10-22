package com.loftymr.whichone.domain.repository

import com.loftymr.whichone.data.model.RingsOfThePowerResponse
import com.loftymr.whichone.data.remote.source.BaseRemoteDataSource
import com.loftymr.whichone.data.remote.api.WhichOneService
import com.loftymr.whichone.data.remote.util.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by talhafaki on 9.09.2022.
 */

class WhichOneRepository @Inject constructor(
    private val whichOneService: WhichOneService
) : BaseRemoteDataSource() {

    suspend fun ringsOfThePowerSurvey(): Flow<DataState<RingsOfThePowerResponse>> =
        getResult {
            whichOneService.getRingsOfThePowerSurveys()
        }
}