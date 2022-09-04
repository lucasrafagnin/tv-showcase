package com.rafagnin.tvshowcase.data.model.json

import com.squareup.moshi.Json

data class CountryJson(
    @Json(name = "name") val name: String
)
