package com.loftymr.whichone.feature.screen.result

import androidx.lifecycle.ViewModel
import com.loftymr.whichone.util.AppCache
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by talhafaki on 25.10.2022.
 */
@HiltViewModel
class ResultViewModel @Inject constructor(
    val appCache: AppCache
): ViewModel() {
}