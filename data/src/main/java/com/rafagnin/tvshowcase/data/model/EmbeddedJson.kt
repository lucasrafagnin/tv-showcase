package com.rafagnin.tvshowcase.data.model

import com.squareup.moshi.Json

data class EmbeddedJson(
    @Json(name = "episodes") val episodes: List<EpisodeJson>,
    @Json(name = "cast") val cast: List<CastJson>,
)
