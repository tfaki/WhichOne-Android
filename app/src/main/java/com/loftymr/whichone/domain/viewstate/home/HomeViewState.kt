package com.loftymr.whichone.domain.viewstate.home

import com.loftymr.whichone.data.model.Category
import com.loftymr.whichone.domain.viewstate.WhichOneViewState

/**
 * Created by talhafaki on 25.10.2022.
 */

data class HomeViewState(
    var isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: List<Category>? = null
) : WhichOneViewState