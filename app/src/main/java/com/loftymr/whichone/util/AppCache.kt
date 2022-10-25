package com.loftymr.whichone.util

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by talhafaki on 25.10.2022.
 */

@Singleton
class AppCache @Inject constructor() {

    var surveyId: String? = null
    var surveyTitle: String? = null
}