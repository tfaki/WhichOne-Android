package com.loftymr.whichone.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by talhafaki on 25.10.2022.
 */

data class CategoryResponse(
    @SerializedName("cat_id")
    val catId: String? = null,
    @SerializedName("back_img")
    val catImage: String? = null,
    @SerializedName("title")
    val title: String? = null
)