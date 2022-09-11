package com.loftymr.whichone.data.di

import android.content.Context
import com.loftymr.whichone.WhichOneApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by talhafaki on 9.09.2022.
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): WhichOneApp {
        return app as WhichOneApp
    }
}