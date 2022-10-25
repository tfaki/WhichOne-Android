package com.loftymr.whichone.data.remote.api

import com.loftymr.whichone.data.model.Category
import com.loftymr.whichone.data.model.SurveyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by talhafaki on 9.09.2022.
 */

interface WhichOneService {
    @GET("")
    suspend fun getCategories(): Response<List<Category>>

    @GET("")
    suspend fun getSurvey(
        @Query("surveyid") id: String,
        @Query("count") count: Int = 10
    ): Response<SurveyResponse>
}