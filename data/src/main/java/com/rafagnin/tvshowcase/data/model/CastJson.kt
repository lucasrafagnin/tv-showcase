package com.rafagnin.tvshowcase.data.model

import com.squareup.moshi.Json

data class CastJson(
    @Json(name = "person") val person: PersonJson,
)
