package com.rafagnin.tvshowcase.domain.model

import java.io.Serializable

data class EpisodeModel(
    val id: Long,
    val name: String?,
    val description: String?,
    val image: String?,
    val season: String?,
    val episode: String?,
    val airdate: String?,
    val hourAirdate: String?,
    val runtime: Int?,
    val show: ShowModel?
): Serializable
