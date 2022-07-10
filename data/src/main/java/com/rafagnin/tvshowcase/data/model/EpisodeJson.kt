package com.rafagnin.tvshowcase.data.model

import com.squareup.moshi.Json

data class EpisodeJson(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "summary") val summary: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "season") val season: String?,
    @Json(name = "number") val number: String?,
    @Json(name = "airdate") val airdate: String?,
    @Json(name = "airstamp") val airstamp: String?,
    @Json(name = "runtime") val runtime: Int?,
    @Json(name = "image") val image: ImageJson?,
    @Json(name = "_embedded") val embedded: EmbeddedJson?
)
