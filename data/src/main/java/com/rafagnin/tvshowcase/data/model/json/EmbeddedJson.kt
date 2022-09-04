package com.rafagnin.tvshowcase.data.model.json

import com.squareup.moshi.Json

data class EmbeddedJson(
    @Json(name = "episodes") val episodes: List<EpisodeJson>?,
    @Json(name = "cast") val cast: List<CastJson>?,
    @Json(name = "show") val show: ShowJson?,
)
