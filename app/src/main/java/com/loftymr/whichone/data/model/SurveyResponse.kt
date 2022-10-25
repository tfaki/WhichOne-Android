package com.loftymr.whichone.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by talhafaki on 9.09.2022.
 */

data class SurveyResponse(
    @SerializedName("questions")
    val questions: List<Questions>? = null,
    @SerializedName("charecter")
    val character: Character? = null,
    @SerializedName("backGroundPictures")
    val backgroundPictures: List<String>? = null
)

data class Questions(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("questionText")
    val questionText: String? = null,
    @SerializedName("choices")
    val choices: List<String>? = null
)

data class Character(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("srcset")
    val srcSet: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("description_reason")
    val descriptionReason: String? = null
)

data class SrcSet(
    @SerializedName("320w")
    val threeHundred: String? = null,
    @SerializedName("480w")
    val fourHundred: String? = null,
    @SerializedName("650w")
    val sixHundred: String? = null,
    @SerializedName("970w")
    val nineHundred: String? = null
)