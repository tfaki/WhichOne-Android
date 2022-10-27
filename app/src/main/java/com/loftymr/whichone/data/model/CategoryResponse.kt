package com.loftymr.whichone.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by talhafaki on 25.10.2022.
 */

@Keep
data class CategoryResponse(
    @SerializedName("cat_id")
    val catId: String? = null,
    @SerializedName("back_img")
    val catImage: String? = null,
    @SerializedName("title")
    val title: String? = null
)