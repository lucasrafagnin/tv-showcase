package com.rafagnin.tvshowcase.data.model

import com.squareup.moshi.Json

data class PersonJson(
    @Json(name = "name") val name: String,
    @Json(name = "image") val image: ImageJson?
)
