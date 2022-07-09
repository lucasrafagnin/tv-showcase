package com.rafagnin.tvshowcase.domain.model

data class EpisodeModel(
    val id: Long,
    val name: String?,
    val image: String?,
    val season: String?,
    val number: String?,
    val airdate: String?,
)
