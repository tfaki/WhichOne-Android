package com.loftymr.whichone.data.remote.api

import com.loftymr.whichone.data.model.RingsOfThePowerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by talhafaki on 9.09.2022.
 */

interface SurveyService {
    @GET("")
    suspend fun getRingsOfThePowerSurveys(
        @Query("count") count: Int = 10
    ): Response<RingsOfThePowerResponse>
}