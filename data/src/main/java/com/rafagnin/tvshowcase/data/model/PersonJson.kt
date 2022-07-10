package com.rafagnin.tvshowcase.data.model

import com.squareup.moshi.Json

data class PersonJson(
    @Json(name = "name") val name: String,
    @Json(name = "birthday") val birthday: String?,
    @Json(name = "gender") val gender: String?,
    @Json(name = "image") val image: ImageJson?,
    @Json(name = "country") val country: CountryJson?,
    @Json(name = "url") val url: String
)
