package com.rafagnin.tvshowcase.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class SearchJson(
    @Json(name = "show") val show: ShowJson,
    @Json(name = "score") val score: Float?,
)
