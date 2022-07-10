package com.rafagnin.tvshowcase.domain.model

data class EpisodeModel(
    val id: Long,
    val name: String?,
    val image: String?,
    val season: String?,
    val episode: String?,
    val airdate: String?,
    val hourAirdate: String?,
    val show: ShowModel?
)
