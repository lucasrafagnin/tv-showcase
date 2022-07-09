package com.rafagnin.tvshowcase.data.model

import com.squareup.moshi.Json

data class ImageJson(
    @Json(name = "medium") val medium: String,
    @Json(name = "original") val original: String
)
