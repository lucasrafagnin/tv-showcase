package com.rafagnin.tvshowcase.data.model

import com.squareup.moshi.Json

data class ShowJson(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "status") val status: String,
    @Json(name = "summary") val summary: String,
    @Json(name = "airdate") val airdate: String,
    @Json(name = "premiered") val premiered: String,
    @Json(name = "averageRuntime") val averageRuntime: String,
    @Json(name = "genres") val genres: List<String>,
    @Json(name = "_embedded") val embedded: EmbeddedJson?,
    @Json(name = "image") val image: ImageJson
)
