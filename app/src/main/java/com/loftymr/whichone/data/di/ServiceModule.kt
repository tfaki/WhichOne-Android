package com.loftymr.whichone.data.di

import com.loftymr.whichone.data.remote.api.WhichOneService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

/**
 * Created by talhafaki on 9.09.2022.
 */

@Module
@InstallIn(ViewModelComponent::class)
class ServiceModule {

    @Provides
    fun provideSurveyService(retrofit: Retrofit): WhichOneService =
        retrofit.create(WhichOneService::class.java)
}