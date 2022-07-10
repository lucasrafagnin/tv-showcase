package com.rafagnin.tvshowcase.data.model

import com.squareup.moshi.Json

data class RatingJson(
    @Json(name = "average") val average: Float?,
)
